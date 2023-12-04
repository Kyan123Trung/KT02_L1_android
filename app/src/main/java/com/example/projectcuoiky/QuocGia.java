package com.example.projectcuoiky;


public class QuocGia {
    String nameCommon, nameOfficial, capital, languages, quocky, chauluc;
    int population;

    public String getChauluc() {
        return chauluc;
    }

    public void setChauluc(String chauluc) {
        this.chauluc = chauluc;
    }

    public QuocGia() {
    }

    public QuocGia(String nameCommon, String nameOfficial, String capital, String languages, String quocky, int population, String chauluc) {
        this.nameCommon = nameCommon;
        this.nameOfficial = nameOfficial;
        this.capital = capital;
        this.languages = languages;
        this.quocky = quocky;
        this.population = population;
        this.chauluc = chauluc;
    }

    public String getNameCommon() {
        return nameCommon;
    }

    public void setNameCommon(String nameCommon) {
        this.nameCommon = nameCommon;
    }

    public String getNameOfficial() {
        return nameOfficial;
    }

    public void setNameOfficial(String nameOfficial) {
        this.nameOfficial = nameOfficial;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public String getLanguages() {
        return languages;
    }

    public void setLanguages(String languages) {
        this.languages = languages;
    }

    public String getQuocky() {
        return quocky;
    }

    public void setQuocky(String quocky) {
        this.quocky = quocky;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }
}
