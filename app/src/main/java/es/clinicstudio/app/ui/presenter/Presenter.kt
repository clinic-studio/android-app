package es.clinicstudio.app.ui.presenter

import es.clinicstudio.app.ui.view.View

/**
 * Default presenter implementation for every presenter within the app.
 *
 * @author vh @ recursividad.es
 */
abstract class Presenter<V: View> {

    /**
     * Set the view that will display the data.
     *
     * @param view View.
     */
    abstract fun setView(view: V)
}