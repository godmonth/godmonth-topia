package com.godmonth.topia.data.page;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public class DataLooperTest {
	@Test
	public void loopByPage() {
		JpaSpecificationExecutor executor = Mockito.mock(JpaSpecificationExecutor.class);
		Mockito.when(executor.count(Mockito.any(Specification.class))).thenReturn(11L);

		List<String> s = Arrays.asList("a", "b");
		Mockito.when(executor.findAll(Mockito.any(Specification.class), Mockito.any(PageRequest.class)))
				.thenReturn(new PageImpl<>(s));
		Specification specification = Mockito.mock(Specification.class);
		DataLooper.loopByPage(executor, specification, System.out::println, 10, null);
	}

	@Test
	public void reverseLoopByPage() {
		JpaSpecificationExecutor executor = Mockito.mock(JpaSpecificationExecutor.class);
		Mockito.when(executor.count(Mockito.any(Specification.class))).thenReturn(11L);

		List<String> s = Arrays.asList("a", "b");
		Mockito.when(executor.findAll(Mockito.any(Specification.class), Mockito.any(PageRequest.class)))
				.thenReturn(new PageImpl<>(s));
		Specification specification = Mockito.mock(Specification.class);
		DataLooper.reverseLoopByPage(executor, specification, System.out::println, 10, null);
	}
}
