#include "Estructuras.h"

int CrearCaja(caja Arreglo[], int dimension)   ///CREA Y CARGA LAS 12 CAJAS, 2 DE CADA TIPO
{
    int i=0;
    int a=1;

    while(i<dimension)
    {

        while(i<2)  ///FIFO
        {
            CargaCaja(Arreglo,"SEBA",1,a,i);
            strcpy(Arreglo[i].algoritmoPlanificacion, "FIFO");
            i++;
            a++;
        }
        while(i<4)  ///SJF
        {
            CargaCaja(Arreglo,"JUAN",1,a,i);
            strcpy(Arreglo[i].algoritmoPlanificacion, "SJF");
            a++;
            i++;
        }
        while(i<6)  ///SRTF
        {
            CargaCaja(Arreglo,"RAMIRO",2,a,i);
            strcpy(Arreglo[i].algoritmoPlanificacion, "STRF");
            a++;
            i++;
        }
        while(i<8)  ///Prioridades apropiativo
        {
            CargaCaja(Arreglo,"BRUNO",2,a,i);
            strcpy(Arreglo[i].algoritmoPlanificacion, "Prioridades apropiativo");
            a++;
            i++;
        }
        while(i<10)  ///Prioridades no apropiativo
        {
            CargaCaja(Arreglo,"MATEO",3,a,i);
            a++;
            strcpy(Arreglo[i].algoritmoPlanificacion, "Prioridades no apropiativo");
            i++;
        }
        while(i<12) /// RR con Quantum
        {
            CargaCaja(Arreglo,"KEVIN",3,a,i);
            ///QUANTUM 5
            strcpy(Arreglo[i].algoritmoPlanificacion, "RR con Quantum 5");
            a++;
            i++;
        }
    }

    return i;
}
void CargaCaja(caja Arreglo[], char nombreCajero[],int tipoDePago,int NumCaja,int indice)  /// CARGA UNA CAJA
{

    Arreglo[indice].nro_de_caja=NumCaja;
    strcpy(Arreglo[indice].nombreCajero, nombreCajero);
    Arreglo[indice].tipo_pago=tipoDePago;
    Arreglo[indice].abiertaOcerrada=0;   ///0=CAJA ABIERTA ---- 1=CAJA CERRADA
    inicFila(&Arreglo[indice].filita);
}


void UnaCaja(caja arreglo[], int indice)  ///MUESTRA UNA CAJA
{
    puts("\n=====================================================\n");
    printf("\t CAJA N %d\n\n", indice);
    printf("Nombre del Cajero:   %s\n\n",arreglo[indice].nombreCajero);
    if(arreglo[indice].tipo_pago ==1)
        printf("Tipo de pago:  Efectivo \n\n");
    if(arreglo[indice].tipo_pago ==2)
        printf("Tipo de pago:  Credito o Debito \n\n");
    if(arreglo[indice].tipo_pago ==3)
        printf("Tipo de pago:  Todo \n\n");
    printf("Algoritmo de planificacion: %s\n\n",arreglo[indice].algoritmoPlanificacion);

    if(arreglo[indice].abiertaOcerrada ==0)
        printf("Caja Abierta\n");
    if(arreglo[indice].abiertaOcerrada ==1)
        printf("Caja Cerrada\n");
    puts("\n======================================================\n");
    mostrarFila(&arreglo[indice].filita);
    printf("\t");

}


void MostrarArreglo(caja Arreglo[], int validos)
{

    int i=0;
    for(i=0; i<validos; i++)
    {
        printf("Numero de caja:    %d",Arreglo[i].nro_de_caja);
        UnaCaja(Arreglo, i);
    }
}

void AbrirOCerrarCajas(caja Arreglo[]) /// ABRE O CIERRA UNA CAJA
{

    int aux=0;
    int aux2=0;
    int a=0;
    printf("\n Desea abrir o cerrar una caja? \n");
    printf("\n 1-> Abrir\n");
    printf(" 2-> Cerrar\n");
    printf("\n--->>OPCION:  ");
    fflush(stdin);
    scanf("%d", &aux);
    printf("\nIngrese el numero de la caja:  ");
    fflush(stdin);
    scanf("%d", &aux2);

    if(&Arreglo[aux2-1].filita!=NULL)
    {

        if(aux==1)
        {

            Arreglo[aux2-1].abiertaOcerrada=0;
            a=1;
        }
        else if(aux==2)
        {
            Arreglo[aux2-1].abiertaOcerrada=1;;
            a=1;
        }
        else
        {
            printf("NUMERO INCORRECTO.\n");
        }

    }
    else
    {
        printf("\nNO PUEDE CERRAR UNA CAJA CON CLIENTES DENTRO.\n");

    }
    if(a!=0){
    UnaCaja(Arreglo,aux2-1);
    printf("\nOPERACION EXITOSA.");
    }
}

int BuscarCaja(caja arreglo[], int validos, char TipoDePago[])   ///BUSCA UNA CAJA POR SU TIPO DE ALGORITMO
{


    int i=0;
    int aux=-1;
    while(i<validos && aux!=i)
    {

        if(arreglo[i].abiertaOcerrada==0)
        {

            if(strcmp(arreglo[i].algoritmoPlanificacion,TipoDePago)==0)
            {

                aux=i;
            }

        }
        i++;
    }
    return aux;  ///retorna posicion de la caja
}
int BuscaMejorCaja(caja Arreglo[], int validos, persona aux)
{

    int i=0;
    int cut=0;
    int cantidad=0;
    int posicion=0;
    int aux_cantidad=1000;

    for(i=0; i<validos; i++)
    {

        if(Arreglo[i].abiertaOcerrada==0)
        {

            if(Arreglo[i].tipo_pago==aux.medioPago)
            {
                cantidad=contarCantFila(&Arreglo[i].filita);
                if(cantidad < aux_cantidad)
                {
                    aux_cantidad=cantidad;
                    posicion=i;
                }
                cut=1;
            }
            if(Arreglo[i].tipo_pago ==3 && cut==0)
            {
                cantidad=contarCantFila(&Arreglo[i].filita);
                if(cantidad < aux_cantidad)
                {
                    aux_cantidad=cantidad;
                    posicion=i;
                }
            }
        }
    }
    return posicion;
}
void agregarClienteAcaja(caja arreglo[],persona aux,int PosmejorCaja)
{
    PosmejorCaja=BuscaMejorCaja(arreglo,12,aux);

    if(strcmpi(arreglo[PosmejorCaja].algoritmoPlanificacion,"strf")==0)
    {
        agregarEnFilaSTRF(&arreglo[PosmejorCaja].filita,aux);
    }
    if(strcmpi(arreglo[PosmejorCaja].algoritmoPlanificacion,"sjs")==0)
    {
        agregarEnFilaSTRF(&arreglo[PosmejorCaja].filita,aux);
    }
    else if(strcmpi(arreglo[PosmejorCaja].algoritmoPlanificacion,"prioridades apropiativo")==0)
    {
        agregarEnFilaPrioridadApropiativo(&arreglo[PosmejorCaja].filita,aux);
    }
    else
    {
        agregarEnFilaAlFnal(&arreglo[PosmejorCaja].filita,aux);
    }
}
///FUNCIONES DE PROCESAR CAJAS
void MostrarFilasCajas (caja arreglo[])
{
    int contador=0;

    while(contador<12)
    {
        if(filavacia(&arreglo[contador].filita)!=0)
        {
            printf("\n---- CAJA %s  N %d -------\n\n ",arreglo[contador].algoritmoPlanificacion, contador+1);
            mostrarFila(&arreglo[contador].filita);
        }
        contador++;
    }
}
///FUNCIONES DE PROCESAR CAJAS

void ProcesarCajas(caja arreglo[], int validos)
{

    atenderCliente(arreglo,validos);
    MostrarFilasCajas(arreglo);

}

void atenderCliente(caja cajita[], int cargados)
{
    int i=0;

    system("cls");
    printf("\t\t----Clientes Atendidos----\n\n");

    while(i < cargados)
    {
        if(cajita[i].abiertaOcerrada == 0)
        {
            if(filavacia(&cajita[i].filita) != 0)        ///si la lista tiene clientes
            {
                if(strcmp(cajita[i].algoritmoPlanificacion, "RR con Quantum 5") == 0)
                {
                    procesoRR(&cajita[i].filita);
                }
                else
                {
                    restoDeProcesos(&cajita[i].filita);
                    //cajita[i].filita->inicio->cliente.tiempoProcesado=cajita[i].filita->inicio.cliente.cantArticulos;
                    //cajita[i].filita->inicio->cliente.cantArticulos=0;
                }
                //incrementarTiempoRespuesta(&cajita[i].filita->inicio);
            }
            /* else
             {
                 char letra='s';
                 printf("\nLa Caja num. %d esta vacia (no tiene clientes), quiere cerrar la caja? S/N: ", i+1);
                 fflush(stdin);
                 scanf("%d", &letra);

                 if(letra == 's')
            cajita[i].abiertaOcerrada= 0;*/
        }
        i++;
    }
}

void procesoRR(Fila * aux)
{
    nodo * dato=aux->inicio;
    int quantum=5;
    int acumulador=0;

    while(dato!=NULL)
    {
        while(dato->cliente.cantArticulos != 0)
        {
            if(dato->cliente.tiempoProcesado < quantum)
            {
                //nodo* aux= crearNodo(quitarDeFila(filita));
                //filita= agregarAlFinal(filita, aux);
                dato->cliente.tiempoProcesado=dato->cliente.cantArticulos;
                dato->cliente.cantArticulos=0;
            }
            else if(dato->cliente.cantArticulos > quantum)
            {
                dato->cliente.tiempoProcesado=dato->cliente.tiempoProcesado+5;
                dato->cliente.cantArticulos=dato->cliente.cantArticulos - dato->cliente.tiempoProcesado;
            }
        }
        acumulador=(acumulador + dato->cliente.tiempoProcesado);
            nodo *sig;
            if(dato->siguiente!=NULL)
            {
                sig=dato->siguiente;
                sig->cliente.tiempoEspera=acumulador;
            }
        dato=dato->siguiente;
    }
}

void restoDeProcesos(Fila * aux)
{
    nodo * dato=aux->inicio;
    int acumulador=0;
    while(dato!=NULL)
    {
        if(dato->cliente.cantArticulos != 0)
        {
            dato->cliente.tiempoProcesado=dato->cliente.cantArticulos;

            acumulador=(acumulador + dato->cliente.tiempoProcesado);
            nodo *sig;
            if(dato->siguiente!=NULL)
            {
                sig=dato->siguiente;
                sig->cliente.tiempoEspera=acumulador;
            }
        }
        dato=dato->siguiente;
        //borrarNodoPrimero(dato);
    }
}

void vaciarCaja(caja arreglo[])///VACIA UNA SOLA CAJA
{
    int opcion=0;
    int i=0;
    printf("Ingrese el numero de la caja a vaciar :  \n");
    scanf("%d",&opcion);
    opcion--;
    while(arreglo[opcion].filita.inicio!=NULL)
    {
        quitarDeFila(&arreglo[opcion].filita);///QUITA EL PRIMER DATO DE LA FILA
        i=1;
    }
    if(i==1){
            printf("\n  SE VACIO LA CAJA CORRECTAMENTE \n");
    }
}
void vaciarTodasLasCajas(caja arreglo[])///VACIA TODAS LAS CAJAS
{
    int i=0;
    while(i<12)
    {
        while(arreglo[i].filita.inicio!=NULL)
        {
            quitarDeFila(&arreglo[i].filita);
        }
        i++;
    }
}
void menuSupermecado(caja arreglo[])
{
    int i=0;
    int aux=0;
    int opcion=0;
    persona p;
    int posmejorcaja=0;
    do
    {
        system("cls");
        i=0;
        printf("\n  ----------------->    BIENVENIDO AL SUPERMERCADO    <------------------\n\n\n");
        printf("\nOPCION 1 : VER CAJA. \n");
        printf("\nOPCION 2 : CERRAR O ABRIR CAJA. \n");
        printf("\nOPCION 3 : INGRESAR PERSONAS A CAJAS.\n");///NO FUNCIONA
        printf("\nOPCION 4 : PROCESAR CAJA.\n");
        printf("\nOPCION 5 : VACIAR UNA CAJA.\n");
        printf("\nOPCION 6 : REINICIAR CAJAS.\n");
        printf("\nOPCION 7 : VOLVER.\n");
        printf("\nINGRESE OPCION DESEADA:  ");
        scanf("%d",&opcion);
        switch(opcion)
        {
        case 1:
            system("cls");
            while(i<12)
            {
                UnaCaja(arreglo,i);
                if(i==6){
                    system("pause");
                }
                i++;
            }
            printf("\n");
            system("pause");
            break;
        case 2:
            system("cls");
            AbrirOCerrarCajas(arreglo);
            break;
        case 3:
            system("cls");
            agregarClienteEnTiempoDeterminado(arreglo, p);
            break;
        case 4:
            system("cls");
            ProcesarCajas(arreglo,12);
            system("pause");
            break;
        case 5:
            vaciarCaja(arreglo);
            system("pause");
            break;
        case 6:
            vaciarTodasLasCajas(arreglo);
            break;
        case 7:
            system("cls");
            break;
        default:
            printf("Opcion incorrecta reintente...\n");
            break;
        }
    }
    while(opcion!=7);
}
persona CargaUnCliente (){

    persona a;
    int id=18;
    printf("\n\t    REGISTRO CLIENTE  ");
    printf("\n\t========================\n\n");
    printf("Su Identificacion Personal(I.D):%d\n", id);
    a.id=id;
    printf("\nIngrese su Nombre: ");
    fflush(stdin);
    gets(a.nombres);
    printf("\nIngrese su apellido: ");
    fflush(stdin);
    gets(a.apellido);
    do
    {
        printf("\nTipo de cliente : \n 1->Embarazada \n 2->Jubilado \n 3->Comun \n\n-->>OPCION:  ");
        scanf("%d",&a.tipoCliente);
    }
    while(a.tipoCliente<1 || a.tipoCliente>3);
    do
    {
        printf("\nIngrese medio de pago: \n 1->Efectivo \n 2->Credito \n 3->Todos \n\n-->>OPCION:  ");
        scanf("%d",&a.medioPago);
    }
    while(a.medioPago<1 || a.medioPago>3);
    printf("\nCantidad de articulos: ");
    scanf("%d",&a.cantArticulos);
    a.eliminado=0;
    a.tiempoEspera=0;
    a.tiempoProcesado=0;

return a;
}
void agregarClienteEnTiempoDeterminado(caja arreglo[], persona p)
{
    int pos;
    int numCaja;
    p=CargaUnCliente();
    printf("\nINGRESE LA CAJA EN LA QUE DESEA INGRESAR LA PERSONA: ");
    fflush(stdin);
    scanf("%i",&numCaja);
    printf("\nINGRESE EN EL TIEMPO QUE DESEA INGRESAR LA PERSONA: ");
    fflush(stdin);
    scanf("%i",&pos);

    if(strcmpi(arreglo[numCaja].algoritmoPlanificacion, "FIFO")==0){
        agregarEnFilaAlFnal(&arreglo[numCaja].filita, p);
        UnaCaja(arreglo,numCaja);

    }else if(strcmpi(arreglo[numCaja].algoritmoPlanificacion, "SJF")==0){
       agregarEnTiempoSJF(&arreglo[numCaja].filita,p,pos);
        mostrarFila(&arreglo[numCaja].filita);
        system("pause");

    }else if(strcmpi(arreglo[numCaja].algoritmoPlanificacion, "SRTF")==0){
        agregarEnTiempoSTRF(&arreglo[numCaja].filita,p,pos);
       UnaCaja(arreglo,numCaja);

    }else if(strcmpi(arreglo[numCaja].algoritmoPlanificacion, "Prioridades apropiativo")==0){
        agregarEnTiempoPrioridadApropia(&arreglo[numCaja].filita,p,pos);
       UnaCaja(arreglo,numCaja);


    }


}


