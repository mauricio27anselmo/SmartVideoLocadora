package br.com.locadora.bean;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

abstract class SmartLocadoraListBean extends SmartLocadoraBean {

    public void addEntity() {
        throw new NotImplementedException();
    }

    public void delete() {
        throw new NotImplementedException();
    }

    protected void list() {
        throw new NotImplementedException();
    }
}
