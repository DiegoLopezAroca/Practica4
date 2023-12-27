package practica;

import java.lang.reflect.Array;
import java.util.ArrayList;
import practica.BaseDeDatosCartas;

public class Recursividad {
	
	static BaseDeDatosCartas base;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

//		String invertida = invertirFrase("hola");
//		String invertida2 = invertirFrase("hola soy Diego");
//		String invertida3 = invertirPalabras("Hola soy Diego");
//		System.out.println(invertida);
//		System.out.println(invertida2);
//		System.out.println(invertida3);
		ArrayList <Cartas> comb = crearBaraja();
		//posiblesManos(1, comb);
		filtrarManosUnReyMinimo(2, comb, "R");
		//base = new BaseDeDatosCartas();
		//guardaManos(1, comb); //obligatoriamente ponemos 1 para que solo se guarden las cartas que hay
	}

	private static void guardaManos(int nCartas, ArrayList<Cartas> baraja) {
        if (nCartas <= 0 || baraja.size() < nCartas) {
            return;
        }
        //tabla 2
        String condicion = "R";
        //ArrayList<Cartas> combFiltro = filtrarManosUnReyMinimo(2, baraja, condicion);
        int codigoFiltro = nCartas;  // Cambia esto según tu lógica para asignar códigos de filtro
		String textoExplicativo = "Tiene que haber minimo un " + condicion;  // Cambia esto según tu lógica
        base.insertarFiltro(codigoFiltro, textoExplicativo);
        //tabla 3
        base.insertarManoFiltro(codigoFiltro);
        //tabla 1
        ArrayList<ArrayList<Cartas>> combinaciones = generarCombinaciones(nCartas, baraja);
        // Guardar cada combinación en la base de datos
        for (ArrayList<Cartas> combinacion : combinaciones) {
            for (Cartas carta : combinacion) {
                base.insertarCarta(carta.getNumero(), carta.getPalo());
                base.insertarCarta(condicion, textoExplicativo);
            }
        }
    }

	public static String invertirFrase(String palabra) {
		if(palabra.isEmpty() || palabra.length() == 1) {
			return palabra;
		} else {
			char primeraLetra = palabra.charAt(0);
			String restoFrase = palabra.substring(1);
			return invertirFrase(restoFrase) + primeraLetra;
		}
	}
	
	public static String invertirPalabras(String frase) {
		if(frase.isEmpty() || frase.length() == 0) {
			return frase;
		}
		int indiceseparador = encontrarSeparador(frase);
		if(indiceseparador == -1) {
			return frase;
		} else {
			String palabra = frase.substring(0, indiceseparador);
			String resto = frase.substring(indiceseparador);
			return invertirPalabras(resto) + palabra;
		}
	}
	
	public static int encontrarSeparador(String frase) {
		for(int i = 0; i<frase.length(); i++) {
			char c = frase.charAt(i);
			if(Character.isSpaceChar(c) || "?¿¡!,.".indexOf(c) != -1 || String.valueOf(c) == "\n") {
				return i + 1;
			}
		}
		return -1;
	}
	
	private static void posiblesManos(int nCartas, ArrayList<Cartas> baraja){
		if(nCartas <= 0 || baraja.size()<nCartas) {
			return;
		}
		ArrayList<ArrayList<Cartas>> combinaciones = generarCombinaciones(nCartas, baraja);
        
        // Imprimir las combinaciones en consola
        for (ArrayList<Cartas> combinacion : combinaciones) {
            System.out.println(combinacion);
        }
		
	}
	
	private static ArrayList<ArrayList<Cartas>> generarCombinaciones(int n, ArrayList<Cartas> baraja) {
		ArrayList<ArrayList<Cartas>> res = new ArrayList<>();
		generarCombinacionesConRecursividad(n, baraja, new ArrayList(), res);
		return res;
	}
	
	
	private static void generarCombinacionesConRecursividad(int n, ArrayList<Cartas> baraja, ArrayList<Cartas> combinacionParcial, ArrayList<ArrayList<Cartas>> resultado) {
		if(combinacionParcial.size() == n) {
			resultado.add(new ArrayList<Cartas>(combinacionParcial));
			return;
		}
		for(int i = 0; i<baraja.size(); i++) {
			combinacionParcial.add(baraja.get(i));
			generarCombinacionesConRecursividad(n, baraja, combinacionParcial, resultado);
			combinacionParcial.remove(combinacionParcial.size()-1);
		}
	}
	
	private static ArrayList<Cartas> crearBaraja() {
		
		ArrayList<Cartas> baraja = new ArrayList<Cartas>();
		String[] palo = {"Oros", "Copas", "Bastos", "Espadas"};
		String[] numero = {"1", "2", "3", "4", "5", "6", "7", "S", "C", "R"};
		
		for (String p : palo) {
		    for (String num : numero) {
		        baraja.add(new Cartas(p, num));
		    }
		}
		return baraja;
	}
	
	private static void filtrarManosUnReyMinimo(int nCartas, ArrayList<Cartas> baraja, String condicion) {
	    if (nCartas <= 0 || baraja.size() < nCartas) {
	        return;
	    }
	    ArrayList<ArrayList<Cartas>> combinaciones = generarCombinaciones(nCartas, baraja);

	    // Imprimir las combinaciones en consola que contienen al menos un Rey
	    for (ArrayList<Cartas> combinacion : combinaciones) {
	        if (cumpleCondicion(combinacion, condicion)) {
	            System.out.println(combinacion);
	        }
	    }
	}

	private static boolean cumpleCondicion(ArrayList<Cartas> mano, String condicion) {
	    // Verificar si alguna carta en la mano contiene la letra "R"
	    for (Cartas carta : mano) {
	        if (carta.getNumero().contains(condicion)) {
	            return true;
	        }
	    }
	    return false;
	}

}
