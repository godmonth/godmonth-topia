package com.godmonth.topia.basic.curator;

import java.util.function.Supplier;

public interface SingletonRepo<T> extends Supplier<T> {
	void put(T input);

	void delete();
}
