package br.com.locadora.bean;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

abstract class SmartLocadoraFormBean extends SmartLocadoraBean {

    public void save() {
        throw new NotImplementedException();
    }

    protected void loadEntityByIdFromRequest() {
        throw new NotImplementedException();
    }
}
