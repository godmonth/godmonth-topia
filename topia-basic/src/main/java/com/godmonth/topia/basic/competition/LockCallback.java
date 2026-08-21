package com.godmonth.topia.basic.competition;

public interface LockCallback<T> {
	/**
	 *
	 * @return
	 */
	T locked();

	/**
	 * 在外部主动释放锁之前的回调，应该在这个方法内停止锁内逻辑。
	 */
	default void beforeRelease(){
	}

	/**
	 * 因为不可控因素，锁已经被释放，所以停止锁内逻辑
	 */
	default void afterReleased(){
	}

}
