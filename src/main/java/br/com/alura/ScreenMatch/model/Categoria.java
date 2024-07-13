package br.com.alura.ScreenMatch.model;

public enum Categoria {

    ACAO("Action"),
    ROMANCE("Romance"),
    COMEDIA("Comedy"),
    TERROR("Horror"),
    DRAMA("Drama"),
    CRIME("Crime"),
    FICCAO("Fiction");

    private String categoriaOmbd;

    Categoria(String categoriaOmbd) {
        this.categoriaOmbd = categoriaOmbd;

    }

    public static Categoria fromString(String text) {
        for (Categoria categoria : Categoria.values()) {
            if (categoria.categoriaOmbd.equalsIgnoreCase(text)) {
                return categoria;
            }
        }
        throw new IllegalArgumentException("Nenhuma categoria encotrada para a série escolhida: ");
    }
}
