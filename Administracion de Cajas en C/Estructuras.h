#ifndef ESTRUCTURAS_H_INCLUDED
#define ESTRUCTURAS_H_INCLUDED
#include <stdio.h>
#include <stdlib.h>
#include <string.h>


 /// ESTRUCTURA PERSONAS
typedef struct
{
    int id;
    char apellido[30];
    char nombres [40];
    int tipoCliente; 		/// Prioridad 1:embarazada, 2:jubilado y 3:cliente común
    int medioPago;			/// 1:efectivo, 2:crédito y 3:todos
    int cantArticulos;		/// es el tiempo de ejecución
    int tiempoEspera;       /// *** es el tiempo de respuesta
    int tiempoProcesado;    /// *** es el tiempo que ya fue procesado en la línea de caja
    int eliminado;			/// 1: eliminado, 0: activo
} persona;

/// USUARIO
typedef struct
{
    int id;
    char nombre[30];
    char apellido[30];
    char usuario[20];
    int pas[2][5];
    int eliminado;
}Usuario;

/// ARBOLES
typedef struct
{
    persona p;
    struct nodoArbol * izquierdo;
    struct nodoArbol * derecha;

} nodoArbol;


/// LISTAS DOBLES
typedef struct
{
    persona cliente;
    struct nodo*siguiente;
    struct nodo*anterior;

}nodo;

/// FILAS
typedef struct
{
    struct nodo *inicio;
    struct nodo *ultimo;
}Fila;

/// ARREGLO DE CAJAS
typedef struct
{
        int nro_de_caja;
        char nombreCajero[40];
        int tipo_pago;                 //  1 efectivo, 2 crédito o débito, 3 todos
        int abiertaOcerrada;
        char algoritmoPlanificacion[30];  // debe informar que tipo de alg. de planificación utiliza la caja
        Fila filita ;

} caja;

 /// ESTRUCTURA PERSONAS
void cargoPersona(char archivoPersona[]);
void printeaElmodifica();
void bajaPersona(char archivoPersona[],int id);
void muestroPersona(char archivoPersona[]);
void muestro1Persona(char archivoPersona[],int id);
void modificacionPersona (char archivoPersona[],int id);
void menuPersonas(char archivoPersona[]);
/// FILAS
void inicFila(Fila *fila);
void agregarEnFila(Fila *fila,char tipoAlgoritmo[],persona people);
void quitarDeFila(Fila *fila);
void mostrarFila(Fila *fila);
int filavacia(Fila *fila);
void agregarEnFilaAlFnal(Fila *fila,persona people);
void agregarEnFilaPrioridadApropiativo(Fila *fila,persona people);
void agregarEnFilaSTRF(Fila *fila,persona people);
int contarCantFila(Fila *filita);
void agregarEnTiempoSJF(Fila* filita,persona people,int pos);
Fila *agregarEnTiempoSTRF(Fila *filita,persona people,int pos);

/// ARREGLO DE CAJAS

int CrearCaja(caja Arreglo[], int dimension);  ///CREA Y CARGA LAS 12 CAJAS, 2 DE CADA TIPO
void CargaCaja(caja Arreglo[], char nombreCajero[],int tipoDePago,int NumCaja,int indice); /// CARGA UNA CAJA
void AbrirOCerrarCajas(caja Arreglo[]); /// ABRE O CIERRA UNA CAJA
int BuscarCaja(caja arreglo[], int validos, char TipoDePago[]);  ///BUSCA UNA CAJA POR SU TIPO DE ALGORITMO
int BuscaMejorCaja(caja Arreglo[], int validos, persona aux);
void agregarClienteAcaja(caja arreglo[],persona aux,int PosMejorCaja);
///PROCESAMIENTO DE CAJAS

void atenderCliente(caja cajita[], int cargados);
void procesoRR(Fila * dato);
void restoDeProcesos(Fila * dato);
void incrementarTiempoRespuesta(nodo* filita);
void vaciarCaja(caja arreglo[]);
void vaciarTodasLasCajas(caja arreglo[]);
void menuSupermecado(caja arreglo[]);
void ProcesarCajas(caja arreglo[], int validos);

/// LISTAS DOBLES

nodo *iniclista();
nodo *crearNodo(persona cliente);
nodo *agregarAlPrincipio(nodo *lista,nodo *nuevo);
nodo *agregarAlFinal(nodo *lista,nodo *nuevo);
nodo *buscarUltimo(nodo *lista);
nodo *agregarEnOrdenTipoCliente(nodo *lista,nodo *nuevo);
nodo *agregarEnOrdenCantidadArticulos(nodo *lista,nodo *nuevo);
void mostrarLista(nodo *lista);
nodo *borrarNodoPorNombre(nodo *lista);
nodo *borrarNodoPrimero(nodo *lista);

///FUNCIONES DE ESTRUCTURA USUARIO
void funcionMenuUsuarios(char archivoUsuario[]);
void cargarUsuario(char ArchivoUsuario[]);///CARGA UN USUARIO
void Encriptacion (char contrasena[],int matriz[][5]);///FUNCION QUE JUNTA TODAS LAS ENCARGADAS DE ENCRIPTAR
void PasarAMatriz (int matriz[][5], char arreglo []);
int CodigoLetra(char letra);
void MultiplicaMatrices (int matriz [][5]);///RECIBE MATRIZ NUMERICA
void MostrarArregloPass(char arreglo[]);
void Cliente(Usuario a);///SUBPROGRAMA PARA MOSTRAR DATOS DEL USUARIO
void mostrarClientes(char nombreArch[]);///MUESTRAS LOS CLIENTES ACTIVOS
void MostrarMatriz(char titulo[], int matriz[][5]);
void MostrarMatrizFloat(char titulo[], float matriz[][5]);///MUESTRA MATRIZ DE 2X5
void MostrarMatriz2X2 (char titulo[], float matriz[][2]);
void cargarMuchos(char archivousuario[]);///CARGA VARIOS USUARIOS LLAMANDO A CARGARUSUARIO
int VerificarUser (char nombre_archivo[], Usuario a);///VERIFICA SI EL USUARIO ES CORRECTO
int corroborarId(char nombreusuario[],int aux);///CORROBORA SI EL ID INGRESADO ESTA ACTIVO
int CalculaId(char nombrearchivo[]);///RETORNA EL NUMERO DE ID DEL ULTIMO ARCHIVO, PARA CARGAR EL SIGUIENTE ID EN UN NUEVO USUARIO
void modificacionMayor(char archivousuario[]);///MODIFICACION DE LOS USUARIOS
void mostrarInactivos(char archivousuario[]);///MUESTRA LOS CLIENTES QUE FUERON ELIMINADOS
void supmodificacionombre(int numero,char archivousuario[]);///SUPROGRAMA DE MOFICACION
void submodificacionapellido(int numero, char archivousuario[]);///SUPROGRAMA DE MOFICACION
void submodificacionusuario(int numeMaxColumnasro,char archivousuario[]);///SUPROGRAMA DE MOFICACION
void submodificacioncontrasenia(int numero,char archivousuario[]);///SUPROGRAMA DE MOFICACION
void eliminarusuario(char archivousuario[]);///ELIMINA UN USUARIO(MODIFICA LA VARIABLE ELIMINADO)

int Login(char nombre_archivo[]);



/// ARBOLES
nodoArbol *menuArbol(caja arreglo[],char archivoPersona[],nodoArbol *arbol);
void menuPasarArbolAcaja(nodoArbol *arbol,caja arreglo[]);
nodoArbol *inicArbol();
nodoArbol *Insertar(nodoArbol *arbol,persona a);
void printea(persona p); ///MUESTRA UNA PERSONA DEL ARBOL
nodoArbol *DeArchivoAarbol(char archivoPersona[],nodoArbol * arbol);
nodoArbol *BorrarNodoArbol (nodoArbol * arbol,int idDelArbol);
nodoArbol *NodoMasDerecho(nodoArbol *arbol);
nodoArbol *NodoMasIzquierdo(nodoArbol *arbol);
void pasarDeArbolToADFinOrden(nodoArbol *arbol, caja arreglo[]);
void pasarDeArbolToADFpreOrden(nodoArbol *arbol, caja arreglo[]);
void pasarDeArbolToADFpostOrden(nodoArbol *arbol, caja arreglo[]);
void pasarDeArbolToarreglo(nodoArbol *arbol,caja arreglo[]);

#endif // ESTRUCTURAS_H_INCLUDED
