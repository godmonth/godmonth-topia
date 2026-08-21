package com.godmonth.topia.data.utils;

import com.godmonth.topia.concept.scope.time.TimePoint;
import com.godmonth.topia.concept.scope.time.TimeScope;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

/**
 * <p></p >
 *
 * @author shenyue
 */
public class TimeScopeCriteriaBuilder {
    public static List<Predicate> create(Path root, CriteriaBuilder criteriaBuilder, TimeScope timeScope, String propertyName) {
        List<Predicate> predicateList = new ArrayList<>();
        {
            TimePoint from = timeScope.getFrom();
            if (from != null) {
                switch (from.getInclusion()) {
                    case INCLUSIVE:
                        predicateList.add(criteriaBuilder.greaterThanOrEqualTo(root.get(propertyName), from.getTime()));
                        break;
                    case EXCLUSIVE:
                        predicateList.add(criteriaBuilder.greaterThan(root.get(propertyName), from.getTime()));
                        break;
                }
            }
        }
        {
            TimePoint to = timeScope.getFrom();
            if (to != null) {
                switch (to.getInclusion()) {
                    case INCLUSIVE:
                        predicateList.add(criteriaBuilder.lessThanOrEqualTo(root.get(propertyName), to.getTime()));
                        break;
                    case EXCLUSIVE:
                        predicateList.add(criteriaBuilder.lessThan(root.get(propertyName), to.getTime()));
                        break;
                }
            }
        }
        return predicateList;
    }

}
