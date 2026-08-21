package com.godmonth.topia.data.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.collections4.IterableUtils;
import org.apache.commons.collections4.Predicate;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.transaction.TransactionStatus;

import com.godmonth.topia.data.model.IdObject;
import com.godmonth.topia.data.model.RankEnabled;

public class RankMovement2Test {

    @Test
    public void move() {
        ListRankmovement listRankmovement = new ListRankmovement();
        R1 r6 = new R1("f6", 6);
        listRankmovement.setList(
                Arrays.asList(new R1("a1", 1), new R1("b2", 2), new R1("c3", 3), new R1("d4", 4), new R1("e5", 5), r6));
        System.out.println(listRankmovement.getList());
        TransactionStatus mock = Mockito.mock(TransactionStatus.class);
        listRankmovement.move(r6, 1, mock);
        System.out.println(listRankmovement.getList());

    }

    private static class ListRankmovement extends RankMovement2<R1, String, Void> {

        private List<R1> list = new ArrayList<>();

        public List<R1> getList() {
            return list;
        }

        public void setList(List<R1> list) {
            this.list = list;
        }

        @Override
        protected Void getParentId(R1 domain) {
            return null;
        }

        @Override
        protected R1 findByRank(Void parentId, final int rank) {
            return IterableUtils.find(list, new Predicate<R1>() {

                @Override
                public boolean evaluate(R1 object) {
                    return object.getRank() == rank;
                }

            });
        }

        @Override
        protected void updateRank(final String id, int targetRank) {
            R1 find = IterableUtils.find(list, new Predicate<R1>() {

                @Override
                public boolean evaluate(R1 object) {
                    return object.getId().equals(id);

                }

            });
            find.setRank(targetRank);
        }

    }

    private static class R1 implements RankEnabled, IdObject<String> {
        private Integer rank;
        private String id;

        public R1(String id, Integer rank) {
            this.rank = rank;
            this.id = id;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public Integer getRank() {
            return rank;
        }

        public void setRank(Integer rank) {
            this.rank = rank;
        }

        /**
         * @see java.lang.Object#toString()
         */
        public String toString() {
            return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE).append("rank", this.rank)
                    .append("id", this.id).toString();
        }

    }
}
