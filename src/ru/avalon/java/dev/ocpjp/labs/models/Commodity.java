package ru.avalon.java.dev.ocpjp.labs.models;

import ru.avalon.java.dev.ocpjp.labs.core.Builder;
import ru.avalon.java.dev.ocpjp.labs.core.io.RandomFileReader;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Абстрактное представление о товаре.
 */
public interface Commodity {
    /**
     * Возвращает код товара.
     *
     * @return код товара в виде строки.
     */
    String getCode();

    /**
     * Возвращает артикул товара
     *
     * @return артикул товара в виде строки.
     */
    String getVendorCode();

    /**
     * Возвращает наименование товара.
     *
     * @return наименование товара в виде строки.
     */
    String getName();

    /**
     * Возвращает стоимость товара.
     *
     * @return стоимость товара.
     */
    double getPrice();

    /**
     * Возвращает остаток товара на складе.
     *
     * @return остаток товара.
     */
    int getResidue();

    /**
     * Абстрактное представление о реализации эталона
     * проектирования "Строитель" для типа данных {@link Commodity}.
     */
    interface CommodityBuilder extends Builder<Commodity> {

        /**
         * Устанавливает код товара.
         *
         * @param code код товара
         * @return экземпляр типа {@link CommodityBuilder}
         */
        CommodityBuilder code(String code);

        /**
         * Устанавливает артикул товара.
         *
         * @param vendorCode артикул товара
         * @return экземпляр типа {@link CommodityBuilder}
         */
        CommodityBuilder vendorCode(String vendorCode);

        /**
         * Устанавливает наименование товара.
         *
         * @param name наименование товара
         * @return экземпляр типа {@link CommodityBuilder}
         */
        CommodityBuilder name(String name);

        /**
         * Устанавливает стоимость товара.
         *
         * @param price стоимость товара
         * @return экземпляр типа {@link CommodityBuilder}
         */
        CommodityBuilder price(double price);

        /**
         * Устанавливает остаток товара на складе.
         *
         * @param residue остаток товара
         * @return экземпляр типа {@link CommodityBuilder}
         */
        CommodityBuilder residue(int residue);

        /**
         * Возвращает экземпляр типа {@link Commodity}
         * проинициализированный согласно заданной
         * конфигурации.
         *
         * @return экземпляр типа {@link Commodity}
         */
        @Override
        Commodity build();
    }

    /**
     * Выполняет создание заданного количества случайных
     * товаров.
     *
     * @param limit количество товаров в результирующей
     *              коллекции
     * @return коллекцию экземпляров {@link Commodity}
     * @throws IOException в случае ошибки ввода-вывода
     */
    static Collection<Commodity> random(int limit) throws IOException {
        try (RandomFileReader reader = RandomFileReader.fromSystemResource("resources/household.csv")) {

               return Stream.generate(reader::readLine).limit(limit).map(x->valueOf(x)).collect(Collectors.toList());
        }
    }

    /**
     * Возвращает "Строитель", с использованием которого
     * можно создавать экземпляры типа {@link Commodity}.
     *
     * @return экземпляр типа {@link CommodityBuilder}
     */

    static CommodityBuilder builder() {
        return new CommodityImpl.CommodityBuilderImpl();

    }
        /**
         * Выполняет создание экземпляра типа {@link Commodity}
         * из строки.
         *
         * @param string строка, содержащая данные о товаре
         * @return экземпляр типа {@link Commodity}
         */
        static Commodity valueOf (String string){

            List<String> section = Stream.of(string.split(";")).collect(Collectors.toList());

            return Commodity.builder().code(section.get(0)).vendorCode(section.get(1)).name(section.get(2)).residue(Integer.parseInt(section.get(3))).price(Double.parseDouble(section.get(4))).build();

        }

    }
