package com.damo.trastome;

/**
 * Created by HappyDave on 13/01/2015.
 */
public class ModelObsPrestecs {
    public interface OnCanviModelListener {
        public void onNovesDades();
    }

    private OnCanviModelListener observador;
    private ModelCjtPrestecs modelCjtPrestecs;

    public ModelObsPrestecs(ModelCjtPrestecs modelCjtPrestecs) {
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
