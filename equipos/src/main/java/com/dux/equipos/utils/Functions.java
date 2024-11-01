package com.dux.equipos.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class Functions {

    private Functions() {}

    public static String checkIfElementIsInList(List<String> lista, String elemento) {

        return lista.stream()
                .filter(each -> StringUtils.equalsIgnoreCase(elemento, each))
                .findFirst()
                .orElse(StringUtils.EMPTY);
    }
}
