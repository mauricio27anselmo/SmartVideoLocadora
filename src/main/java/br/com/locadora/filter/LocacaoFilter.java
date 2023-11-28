package br.com.locadora.filter;

public class LocacaoFilter extends PageableFilter {

    private boolean isReturn;

    public boolean isReturn() {
        return isReturn;
    }

    public void setReturn(boolean aReturn) {
        isReturn = aReturn;
    }
}
