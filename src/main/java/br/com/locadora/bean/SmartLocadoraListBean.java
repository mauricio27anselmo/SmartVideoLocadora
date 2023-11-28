package br.com.locadora.bean;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

abstract class SmartLocadoraListBean extends SmartLocadoraBean {

    public void addEntity() {
        throw new NotImplementedException();
    }

    public void delete() {
        throw new NotImplementedException();
    }

    public void applyFilter() {
        throw new NotImplementedException();
    }

    protected void initializeDataModel() {
        throw new NotImplementedException();
    }

}
