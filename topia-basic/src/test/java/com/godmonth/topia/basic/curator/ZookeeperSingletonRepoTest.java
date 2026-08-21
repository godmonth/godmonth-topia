package com.godmonth.topia.basic.curator;


import java.io.IOException;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.RetryOneTime;
import org.apache.curator.test.TestingServer;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class ZookeeperSingletonRepoTest {

    private static TestingServer zkTestServer;

    private static CuratorFramework cli;

    private static ZookeeperSingletonRepo<TestObject> zookeeperSingletonRepo;

    @BeforeAll
    public static void startZookeeper() throws Exception {
        zkTestServer = new TestingServer();
        zkTestServer.start();
        cli = CuratorFrameworkFactory.newClient(zkTestServer.getConnectString(), new RetryOneTime(2000));
        cli.start();
        zookeeperSingletonRepo = new ZookeeperSingletonRepo<>();
        zookeeperSingletonRepo.setClazz(TestObject.class);
        zookeeperSingletonRepo.setCuratorFramework(cli);
        zookeeperSingletonRepo.setPath("/ffff");
        zookeeperSingletonRepo.setObjectMapper(new ObjectMapper());
        zookeeperSingletonRepo.init();
    }

    @AfterAll
    public static void stopZookeeper() throws IOException {
        zookeeperSingletonRepo.close();
        cli.close();
        zkTestServer.stop();
    }

    @Test
    public void putAndGet() throws Exception {
        TestObject testObject = zookeeperSingletonRepo.get();
        Assertions.assertNull(testObject);

        testObject = new TestObject();
        testObject.setAge(1);
        testObject.setName("ffff");
        zookeeperSingletonRepo.put(testObject);
        zookeeperSingletonRepo.put(testObject);
        TestObject testObject2 = zookeeperSingletonRepo.get();
        Assertions.assertEquals(testObject2, testObject);
        zookeeperSingletonRepo.delete();
        testObject2 = zookeeperSingletonRepo.get();
        Assertions.assertNull(testObject2);

    }

    public static class TestObject {
        private String name;
        private int age;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        /**
         * @see java.lang.Object#equals(Object)
         */
        public boolean equals(Object object) {
            if (!(object instanceof TestObject)) {
                return false;
            }
            TestObject rhs = (TestObject) object;
            return new EqualsBuilder().append(this.name, rhs.name).append(this.age, rhs.age).isEquals();
        }

    }
}
