package com.damo.trastome.models;

import com.damo.trastome.dao.Prestec;

public class ModelObsPrestec {
    public interface OnCanviModelListener {
        public void onNovesDades();
    }

    private OnCanviModelListener observador;
    private ModelCjtPrestecs modelCjtPrestecs;

    public ModelObsPrestec(ModelCjtPrestecs modelCjtPrestecs) {
        this.modelCjtPrestecs = modelCjtPrestecs;
    }

    public void setOnCanviModelListener(OnCanviModelListener observador) {
        this.observador = observador;
    }

    private void avisaObservador() {
        if (observador != null)
            observador.onNovesDades();
    }

    public void add(Prestec p) {
        modelCjtPrestecs.add(p);
        avisaObservador();
    }

    public void del(long id) {
        modelCjtPrestecs.del(id);
        avisaObservador();
    }
}
