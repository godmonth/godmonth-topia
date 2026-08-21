package com.godmonth.topia.basic.competition;

import java.util.Date;

public interface CompetitionCallback<T> {

	T won(Date executeTime);

}
