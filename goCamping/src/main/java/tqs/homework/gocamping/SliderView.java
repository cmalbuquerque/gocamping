/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tqs.homework.gocamping;

/**
 *
 * @author carolina
 */
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
 
import org.primefaces.event.SlideEndEvent;
 
@ManagedBean
public class SliderView {
    
    private float number=30;
    
    public float getNumber() {
        return number;
    }
 
    public void setNumber(float number) {
        this.number = number;
    }
    
    public void onInputChanged(ValueChangeEvent event) {
        FacesMessage message = new FacesMessage("Input Changed", "Value: " + event.getNewValue());
        FacesContext.getCurrentInstance().addMessage(null, message);
    } 
}
