/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fgarioli.javafx.utils;

import java.time.Duration;

/**
 *
 * @author fernando
 */
public class Functions {

    
    /**
     * Retorna um duration a partir de uma String no seguinte formato: dd:HH:mm:ss
     * @param s
     * @return 
     */
    public static Duration stringToDuration(String s) {
        String[] dur = s.split(":");
        int days = Integer.parseInt(dur[0]);
        int hours = Integer.parseInt(dur[1]);
        int minutes = Integer.parseInt(dur[2]);
        int seconds = Integer.parseInt(dur[3]);
        
        return Duration.ofDays(days).plusHours(hours).plusMinutes(minutes).plusSeconds(seconds);
    }

}
