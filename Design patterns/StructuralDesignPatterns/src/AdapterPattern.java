class AdapterPattern {
    public static void main(String[] args) {
        AdapterPattern client = new AdapterPattern();
        client.run();
    }

    public void run() {
        AdapteeShopping oldInterface = new AdapteeShopping();
        Long cost = oldInterface.request("1234", "321", 123.45);
        System.out.println("Cost: " + cost);
        
        String myCreds = "user/pass";
        ShippingAdapter adapter = new ShippingAdapter(myCreds);
        Long newCost = adapter.request("1234", "321", 123.45);
        System.out.println("New cost: " + newCost);
    }
}

//Old interface
class AdapteeShopping {
    String origen;
    String destino;
    Double peso;
    Long total;
    
    public Long request(String origen, String destino, Double peso) {
        this.origen = origen;
        this.destino = destino;
        this.peso = peso;
        this.total = Math.round(Math.random() * 12345);
        return total;
    }
}

//New interface
class TargetShipping {
    String origen;
    String destino;
    Double peso;
    Long total;    
    
    public void login(String credentials) {
        //ToDo
    }
    
    public void setOrigen(String origen) {
        this.origen = origen;
    }
    
    public void setDestino(String destino) {
        this.destino = destino;
    }
    
    public Long calculate(Double peso) {
        this.peso = peso;
        this.total = Math.round(Math.random() * 54321);
        return total;
    }
}

//Adapter
class ShippingAdapter {
    TargetShipping targetShipping;
    
    ShippingAdapter(String credentials) {
        targetShipping = new TargetShipping();
        targetShipping.login(credentials);
    }
    
    //Utiliza una función similar que la vieja inferfaz pero implementando la nueva interfaz
    public Long request(String origen, String destino, Double peso) {
        targetShipping.setOrigen(origen);
        targetShipping.setDestino(destino);
        return targetShipping.calculate(peso);
    }
}
