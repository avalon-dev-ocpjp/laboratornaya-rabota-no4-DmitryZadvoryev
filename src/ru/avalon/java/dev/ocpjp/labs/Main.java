package ru.avalon.java.dev.ocpjp.labs;

import ru.avalon.java.dev.ocpjp.labs.models.Commodity;

import java.io.IOException;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {

        final Collection<Commodity> commodities = Commodity.random(100);

        Double max = commodities.stream().map(Commodity::getPrice).max(Double::compare).get();
        System.out.println("max price:" + max);

        Integer min = commodities.stream().map(Commodity::getResidue).min(Integer::compare).get();
        System.out.println("commodities with minimal residue: ");
        commodities.stream().filter(x -> x.getResidue() == min).collect(Collectors.toList()).forEach(x -> System.out.println(x.toString()));

        double totalPrice = commodities.stream().mapToDouble(x -> x.getResidue() * x.getPrice()).sum();
        System.out.println("total price: " + totalPrice);

        System.out.println("Commodities with residue less than 5: ");
        commodities.stream().filter(x -> x.getResidue() < 5).collect(Collectors.toList()).forEach(n -> System.out.println(n.toString()));

        Optional<String> longestName = commodities.stream().map(Commodity::getName).collect(Collectors.maxBy((c1, c2) -> Integer.compare(c1.length(), c2.length())));
        System.out.println("longest name: " + longestName.get());

        System.out.println("sorted by code:");
        commodities.stream().sorted((c1, c2) -> Integer.valueOf(c1.getCode()).compareTo(Integer.valueOf(c2.getCode()))).forEach(System.out::println);


        double averagePrice = commodities.stream().mapToDouble(Commodity::getPrice).average().getAsDouble();
        System.out.println("average price: " + averagePrice);


        commodities.stream().map(Commodity::getPrice).sorted().skip(commodities.size() / 2).limit(1).forEach(c -> System.out.format("median: %s\n", c));

    }
}
