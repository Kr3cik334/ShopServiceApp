package entity.parser;

import entity.enums.Material;

import java.util.Locale;

public class MaterialParser {
    public static Material parseStrToMaterial(String str) {
        String material = str.toUpperCase();

        if (material.equals("LEATHER")) {
            return Material.LEATHER;
        }
        else if (material.equals("FUR")) {
            return Material.FUR;
        }
        else if (material.equals("COTTON")) {
            return Material.COTTON;
        }
        else if (material.equals("WOOL")) {
            return Material.WOOL;
        }
        else if (material.equals("POLYESTERS")) {
            return Material.POLYESTERS;
        }
        return Material.POLYESTERS;
    }
}
