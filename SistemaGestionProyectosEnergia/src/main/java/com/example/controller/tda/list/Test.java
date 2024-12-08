package com.example.controller.tda.list;

public class Test {
    public Test() {}
    public static class Entero {
        public int numero;
        public Entero() {}
        public Entero(int numero) { this.numero = numero; }
        public int getNumero() { return numero; }
    
        @Override
        public String toString() { return Integer.toString(numero); }
    }

    public static String arrayToString(Entero[] array) {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < array.length; i++) {
            sb.append(array[i]);
            if (i < array.length - 1) 
            sb.append(", "); 
        }
        return sb.append("]").toString();
    }

    public static Entero[] randomArray(int size) {
        Entero[] array = new Entero[size];
        for(int i = 0; i < size; i++) {
            final Integer entero = (int)Math.round(Math.random()*1000);
            array[i] = new Entero(entero);
        }
        return array;
    }

    public static void main(String[] args) throws Exception {
        LinkedList<Entero> list1 = new LinkedList<>();
        LinkedList<Entero> list2 = new LinkedList<>();
        LinkedList<Entero> list3 = new LinkedList<>();
    
        list1.toList(randomArray(25000));
        list2.toList(list1.toArray());
        list3.toList(list1.toArray());

        System.out.println(arrayToString(list1.toArray()));
        System.out.println("*********************************ARRAY ORDENADO*********************************");

        long time1q = System.currentTimeMillis();
        System.err.println(arrayToString(list1.quickSort("numero", 1).toArray()));
        long time2q = System.currentTimeMillis();

        long time1m = System.currentTimeMillis();
        System.err.println(arrayToString(list1.mergeSort("numero", 1).toArray()));
        long time2m = System.currentTimeMillis();

        long time1s = System.currentTimeMillis();
        System.err.println(arrayToString(list1.shellSort("numero", 1).toArray()));
        long time2s = System.currentTimeMillis();

        Long totalQuick = time2q - time1q;
        Long totalMerge = time2m - time1m;
        Long totalShell = time2s - time1s;

        System.out.println("Tiempo de ejecucion QuickSort: "+ totalQuick +"ms");
        System.out.println("Tiempo de ejecucion MergeSort: "+ totalMerge +"ms");
        System.out.println("Tiempo de ejecucion ShellSort: "+ totalShell +"ms");

        //BUSCAR UN ELEMENTO DENTRO DEL ARREGLO DE NUMERO ALEATORIOS
        //BUSQUEDA BINARIA RETORNA UN SOLO OBJETO
        long timeB1 = System.currentTimeMillis();
        System.out.println("El numero que se ha encontrado es: " + list1.busquedaBinaria("numero", 456));
        long timeB2 = System.currentTimeMillis();
        
        //BUSQUEDA LINEAL BINARIA RETORNA UNA LISTA DE OBJETOS
        long timeBL1 = System.currentTimeMillis();
        System.out.println("El numero que se ha encontrado es: " + list1.busquedaLinealBinaria("numero", 524));
        long timeBL2 = System.currentTimeMillis();

        Long totalB = timeB2 - timeB1;
        Long totalBL = timeBL2 - timeBL1;
        System.out.println("Tiempo Busqueda Binaria: " + totalB + "ms");
        System.out.println("Tiempo Busqueda Lineal Binaria: " + totalBL + "ms");  
    }
}