package com.godmonth.topia.data.page;

import java.util.function.Consumer;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public class DataLooper {

	public static <ITEM> void loopByPage(JpaSpecificationExecutor<ITEM> executor, Specification<ITEM> specification,
			Consumer<ITEM> consumer, int pageSize, Sort sort) {
		int count = (int) executor.count(specification);
		if (count > 0) {
			int totalPages = (int) count / pageSize + (count % pageSize > 0 ? 1 : 0);
			for (int i = 0; i < totalPages; i++) {
				perPage(executor, specification, consumer, i, pageSize, sort);
			}
		}
	}

	public static <ITEM> void reverseLoopByPage(JpaSpecificationExecutor<ITEM> executor,
			Specification<ITEM> specification, Consumer<ITEM> consumer, int pageSize, Sort sort) {
		int count = (int) executor.count(specification);
		if (count > 0) {
			int totalPages = (int) count / pageSize + (count % pageSize > 0 ? 1 : 0);
			for (int i = totalPages; i > 0; i--) {
				perPage(executor, specification, consumer, i, pageSize, sort);
			}
		}
	}

	private static <ITEM> void perPage(JpaSpecificationExecutor<ITEM> executor, Specification<ITEM> specification,
			Consumer<ITEM> consumer, int currentPage, int pageSize, Sort sort) {
		if(sort == null){
			sort = Sort.unsorted();
		}
		PageRequest pageRequest = PageRequest.of(currentPage,pageSize,sort);
		Page<ITEM> page = executor.findAll(specification, pageRequest);
		if (CollectionUtils.isNotEmpty(page.getContent())) {
			page.getContent().stream().forEach(consumer);
		}
	}
}
