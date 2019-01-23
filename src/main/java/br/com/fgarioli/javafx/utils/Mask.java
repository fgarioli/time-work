/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fgarioli.javafx.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author fernando
 * Classe auxiliar para máscaras keyReleased e validação de campos utilizando a biblioteca ControlsFX
 */
public class Mask {

    // O "|" no final das expressões indica que não é obrigatório o preenchiemnto 
    public static final String CPF_CNPJ_REGEX = "([0-9]{2}[\\.]?[0-9]{3}[\\.]?[0-9]{3}[\\/]?[0-9]{4}[-]?[0-9]{2})|([0-9]{3}[\\.]?[0-9]{3}[\\.]?[0-9]{3}[-]?[0-9]{2})|";
    public static final String CPF_REGEX = "([0-9]{3}[\\.]?[0-9]{3}[\\.]?[0-9]{3}[-]?[0-9]{2})|";
    public static final String CNPJ_REGEX = "([0-9]{2}[\\.]?[0-9]{3}[\\.]?[0-9]{3}[\\/]?[0-9]{4}[-]?[0-9]{2})|";
    public static final String CEP_REGEX = "([0-9]{2}[\\.]?[0-9]{3}[-]?[0-9]{3})|";
    public static final String TEL_REGEX = "^(\\([0-9]{2}\\))\\s([9]{1})?([0-9]{4})-([0-9]{4})$|";
    public static final String DATA_REGEX = "^\\d{4}-\\d{2}-\\d{2}$|";
    public static final String EMAIL_REGEX = "[a-z._-]+@[a-z.]+|";

    public static String maskTel(String tel) {
        if (tel.length() == 1) {
            StringBuilder stringBuilder = new StringBuilder(tel);
            stringBuilder.insert(0, '(');
            return stringBuilder.toString();
        } else if (tel.length() == 3) {
            tel = tel + ") ";
            return tel;
        } else if (tel.length() == 9) {
            tel = tel + "-";
            return tel;
        } else if (tel.length() == 15) {
            tel = tel.replace("-", "");
            StringBuilder stringBuilder = new StringBuilder(tel);
            stringBuilder.insert(10, '-');
            return stringBuilder.toString();
        } else if (tel.length() > 15) {
            return tel.substring(0, 15);
        } else {
            return tel;
        }
    }

    public static String maskCpf(String cpf) {
        if (cpf.length() == 3 || cpf.length() == 7) {
            return cpf + ".";
        } else if (cpf.length() == 11) {
            return cpf + "-";
        } else if (cpf.length() > 14) {
            return cpf.substring(0, 14);
        } else {
            return cpf;
        }
    }

    public static String maskCnpj(String cnpj) {
        if (cnpj.length() == 2 || cnpj.length() == 6) {
            return cnpj + ".";
        } else if (cnpj.length() == 10) {
            return cnpj + "/";
        } else if (cnpj.length() == 15) {
            return cnpj + "-";
        } else if (cnpj.length() > 18) {
            return cnpj.substring(0, 18);
        } else {
            return cnpj;
        }
    }

    public static String maskCep(String cep) {
        if (cep.length() == 2) {
            return cep + ".";
        } else if (cep.length() == 6) {
            return cep + "-";
        } else if (cep.length() > 10) {
            return cep.substring(0, 10);
        } else {
            return cep;
        }
    }

    public static String maskCPfCnpj(String cpfCnpj) {
        // Tratando CPF / CNPJ
        cpfCnpj = cpfCnpj.replace(".", "");
        cpfCnpj = cpfCnpj.replace("-", "");
        cpfCnpj = cpfCnpj.replace("/", "");
        if (cpfCnpj.length() == 11) {
            String cpfMask = "###.###.###-##";
            for (int i=0; i<cpfCnpj.length(); i++) {
                cpfMask = cpfMask.replaceFirst("#", cpfCnpj.substring(i, i + 1));
            }
            return cpfMask;
        } else if (cpfCnpj.length() == 14) {
            String cnpjMask = "##.###.###/####-##";
            for (int i=0; i<cpfCnpj.length(); i++) {
                cnpjMask = cnpjMask.replaceFirst("#", cpfCnpj.substring(i, i + 1));
            }
            return cnpjMask;
        }
        return "";
    }
    
}
