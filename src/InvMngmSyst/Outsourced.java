/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InvMngmSyst;

/**
 *
 * @author lucytran
 */
public class Outsourced extends Part
{
    private String compName;
    
    public Outsourced(int parID, String name, double price, int inv, int min, int max, String compName)
    {
        super(parID, name, price, inv, min, max);
        this.compName = compName;
    }
    
    public String getCompName()
    {
        return compName;
    }
 
    public void setCompName(String compName)
    {
        this.compName = compName;
    }
}
