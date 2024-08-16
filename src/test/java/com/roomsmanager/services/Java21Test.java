package com.roomsmanager.services;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

record Tp2<T, K>(T _1, K _2) {

}

record Tp3<T, K, A>(T _1, K _2, A _3) {

}


@RunWith(JUnit4.class)
public class Java21Test {

    @Test
    void check_record() {
        record Human(String name) {
        }

        record House(List<Human> owners) {
        }

        record City(List<House> houses, Human major) {
        }

        final var h1 = new Human("Clark");
        final var h2 = new Human("Lois");
        final var h3 = new Human("Jack");
        final var h4 = new Human("Liza");

        final var house1 = new House(List.of(h1, h2));
        final var house2 = new House(List.of(h3, h4));

        final var newYork = new City(List.of(house1, house2), h1);

        if (newYork instanceof City(var houses, Human(var majorName))) {
            assertThat(houses).isEqualTo(List.of(house1, house2));
            assertThat(majorName).isEqualTo("Clark");
            return;
        }

        assertThat(1).isEqualTo(0);
    }

    class Account {
        double getBalance() {
            return 0;
        }
    }

    class SavingsAccount extends Account {
        double getBalance() {
            return 100;
        }
    }

    class TermAccount extends Account {
        double getBalance() {
            return 1000;
        }
    }

    class CurrentAccount extends Account {
        double getBalance() {
            return 10000;
        }
    }


    double getBalanceWithSwitchPattern(Account account) {
        return switch (account) {
            case null -> throw new RuntimeException("Oops, account is null");
            case SavingsAccount sa -> sa.getBalance();
            case TermAccount ta -> ta.getBalance();
            case CurrentAccount ca -> ca.getBalance();
            default -> account.getBalance();
        };
    }

    @Test
    void check_switch() {

        final var a1 = new Account();
        final var a2 = new SavingsAccount();
        final var a3 = new TermAccount();
        final var a4 = new CurrentAccount();

        assertThat(getBalanceWithSwitchPattern(a1)).isEqualTo(0);
        assertThat(getBalanceWithSwitchPattern(a2)).isEqualTo(100);
        assertThat(getBalanceWithSwitchPattern(a3)).isEqualTo(1000);
        assertThat(getBalanceWithSwitchPattern(a4)).isEqualTo(10000);
    }

    @Test
    void check_threads() {

        try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
            IntStream.rangeClosed(1, 10_000).forEach(i -> {
                executor.submit(() -> {
                    System.out.println("start " + i);
                    try {
                        Thread.sleep(Duration.ofSeconds(1));
                        System.out.println("finish " + i);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
            });
        }


    }
}
