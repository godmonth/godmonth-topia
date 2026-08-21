package com.godmonth.topia.data.page;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;

import com.godmonth.topia.pagination.OneSortPageParam;
import com.godmonth.topia.pagination.PageParam;
import com.godmonth.topia.pagination.SortPageParam;
import com.godmonth.topia.pagination.SortParam;

public class PageUtils {
	private PageUtils() {
	}

	public static PageRequest convert(OneSortPageParam pageParam) {
		return convert((PageParam) pageParam,
				pageParam.getSortParam() != null ? Arrays.asList(pageParam.getSortParam()) : null);
	}

	public static PageRequest convert(SortPageParam pageParam) {
		return convert((PageParam) pageParam, pageParam.getSortParamList());
	}

	public static PageRequest convert(PageParam pageParam) {
		return convert(pageParam, null);
	}

	public static PageRequest convert(PageParam pageParam, List<SortParam> sortParamList) {
		Sort sort = Sort.unsorted();

		if (CollectionUtils.isNotEmpty(sortParamList)) {
			List<Order> orderList = new ArrayList<>();
			for (SortParam sortParam : sortParamList) {
				Order order = new Order(Direction.valueOf(sortParam.getDirection().name()), sortParam.getProperty());
				orderList.add(order);
			}
			sort = Sort.by(orderList);
		}
		return PageRequest.of(pageParam.getNumber(), pageParam.getSize(), sort);
	}

}
