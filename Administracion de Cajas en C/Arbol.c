#include "Estructuras.h"

nodoArbol *inicArbol()
{
    return NULL;
}
nodoArbol *crearArbol(persona a)
{
    nodoArbol *arbol=(nodoArbol*)malloc(sizeof(nodoArbol));
    arbol->izquierdo=NULL;
    arbol->derecha=NULL;
    arbol->p=a;
    return arbol;
}

nodoArbol *Insertar(nodoArbol *arbol,persona a)
{
    if(arbol==NULL)
    {
        arbol=crearArbol(a);
    }
    else
    {
        if(strcmp(a.nombres,arbol->p.nombres)<0)
        {
            arbol->derecha=Insertar(arbol->derecha,a);
        }
        else
            arbol->izquierdo=Insertar(arbol->izquierdo,a);
    }
    return arbol;
}
nodoArbol *DeArchivoAarbol(char archivoPersona[],nodoArbol *arbol)
{
    persona p;
    FILE *archivo;
    archivo=fopen(archivoPersona,"rb");

    if(archivo!=NULL)
    {
        while(fread(&p,sizeof(persona),1,archivo)>0)
        {
            if(p.eliminado==0)
            {
                arbol=Insertar(arbol,p);
            }
        }
    }
    fclose(archivo);
    return arbol;
}
void printea(persona p)
{
    printf("\n Identificacion Personal: %d \n",p.id);
    printf("\n Nombre:   %s \n",p.nombres);
    printf("\n Apellido: %s \n",p.apellido);
    if(p.tipoCliente==1)
        printf("\n Cliente: Embarazada \n");
    if(p.tipoCliente==2)
        printf("\n Cliente: Jubilado  \n");
    if(p.tipoCliente==3)
        printf("\n Cliente: Comun  \n");
    if(p.medioPago==1)
        printf("\n Medio de pago: Efectivo  \n");
    if(p.medioPago==2)
        printf("\n Medio de pago: Credito  \n");
    if(p.medioPago==3)
        printf("\n Medio de pago: Todos  \n");

    printf("\n Cantidad de articulos: %d \n",p.cantArticulos);
    if(p.eliminado==1)
        printf("\n Este usuario ha sido eliminado.\n");
    if(p.eliminado==0)
        printf("\n Persona Activa.\n");
    printf("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");

}
void preOrden(nodoArbol *arbol)
{
    if (arbol!=NULL)
    {
        printea(arbol->p);
        preOrden(arbol->izquierdo);
        preOrden(arbol->derecha);
    }
}
void inorden(nodoArbol *arbol)
{
    if(arbol!=NULL)
    {
        inorden(arbol->izquierdo);
        printea(arbol->p);
        inorden(arbol->derecha);
    }
}
void postOrden(nodoArbol *arbol)
{
    if(arbol!=NULL)
    {
        postOrden(arbol->izquierdo);
        postOrden(arbol->derecha);
        printea(arbol->p);
    }
}
void mostrarArbol(nodoArbol *arbol)
{
    int opcion=0;
    puts("\n\tMENU MOSTRAR ARBOL ");
    puts("   ___________________________\n");
    printf("\nOpcion 1: PreOrden \n");
    printf("\nOpcion 2: Inorden  \n");
    printf("\nOpcion 3: PostOrden \n");
    printf("\n-->>OPCION:  ");
    scanf("%d",&opcion);

    switch(opcion)
    {
    case 1:
        preOrden(arbol);
        break;

    case 2:
        inorden(arbol);
        break;

    case 3:
        postOrden(arbol);
        break;

    default:
        printf("\nNo existe\n");
        break;
    }
}
void pasarDeArbolToADFpostOrden(nodoArbol *arbol,caja arreglo[])
{
    if(arbol!=NULL)
    {
        pasarDeArbolToADFpostOrden(arbol->izquierdo,arreglo);
        pasarDeArbolToADFpostOrden(arbol->derecha,arreglo);
        agregarClienteAcaja(arreglo,arbol->p,0);
    }
}
void pasarDeArbolToADFpreOrden(nodoArbol *arbol,caja arreglo[])
{
    if (arbol!=NULL)
    {
        agregarClienteAcaja(arreglo,arbol->p,0);
        pasarDeArbolToADFpreOrden(arbol->izquierdo,arreglo);
        pasarDeArbolToADFpreOrden(arbol->derecha,arreglo);
    }

}
void pasarDeArbolToADFinOrden(nodoArbol *arbol,caja arreglo[])
{
    if(arbol!=NULL)
    {
        pasarDeArbolToADFinOrden(arbol->izquierdo,arreglo);
        agregarClienteAcaja(arreglo,arbol->p,0);
        pasarDeArbolToADFinOrden(arbol->derecha,arreglo);
    }
}
void menuPasarArbolAcaja(nodoArbol *arbol,caja arreglo[])///sin llamar
{
    int opcion;
    printf("\n1_PASAR EN INORDER.\n2_PASAR EN PREORDER.\n3_PASAR EN POSTORDER.\nINGRESE LA OPCION DESEADA: ");
    scanf("%d",&opcion);
    switch(opcion)
    {
    case 1:
        pasarDeArbolToADFinOrden(arbol,arreglo);
        mostrarFila(&arreglo[0].filita);
        break;
    case 2:
        pasarDeArbolToADFpreOrden(arbol,arreglo);
        break;
    case 3:
        pasarDeArbolToADFpostOrden(arbol,arreglo);
        break;
    }
}
nodoArbol *menuArbol(caja arreglo[],char archivoPersona[],nodoArbol *arbol)
{
    int contador=0;
    int opcion;
    int idArbol;
    int validos;
    do
    {
        system("cls");
        printf("\n           =============================================   ");
        printf("\n           ---------- BIENVENIDO AL MENU ARBOL ---------");
        printf("\n           =============================================\n\n\n");
        printf("\n1>>PASAR DE ARCHIVO A ARBOL.\n\n2>>MOSTRAR ARBOL.\n\n3>>BORRAR UN NODO.\n\n4>>PASAR DE ARBOL A CAJA.\n\n5>>VOLVER\n\nINGRESE OPCION DESEADA: ");
        scanf("%d",&opcion);
        switch(opcion)
        {
        case 1:
            system("cls");
            arbol=DeArchivoAarbol(archivoPersona,arbol);
            printf("\nARBOL CARGADO EXITOSAMENTE\n");
            break;
        case 2:
            system("cls");
            mostrarArbol(arbol);
            printf("\n");
            system("pause");
            break;
        case 3:
            system("cls");
            printf("\n\t BORRAR UN NODO ");
            printf("\n       ==================\n\n");
            printf("\nINGRESE ID DEL USUARIO: ");
            scanf("%d",&idArbol);                       ///REVISAAAAR !!!!!
            arbol=BorrarNodoArbol(arbol,idArbol);
            //mostrarArbol(arbol);
            system("pause");
            system("cls");
            break;
        case 4:
            system("cls");
            validos=CrearCaja(arreglo,12);
            pasarDeArbolToarreglo(arbol,arreglo);
            printf("\nLAS FILAS SE CARGARON EXITOSAMENTE.\n");
            system("pause");
            //menuSupermecado(arreglo);
            system("cls");
            opcion=5;
            break;
        case 5:
            break;
        default:
            system("cls");
            printf("\nOPCION INCORRECTA, INTENTE NUEVAMENTE.");
            break;
        }
    }while(opcion!=5);

    return arbol;
}
void pasarDeArbolToarreglo(nodoArbol *arbol,caja arreglo[])
{
    int opcion=0;
        puts("\n\t----->>>>PASA DE ARBOL A CAJA <<<<-------");
        printf("\n\n1->Pasar por inorden.\n\n2->Pasar por preOrden\n\n3->Pasar por postOrden\n\n -->>OPCION: ");
        scanf("%d",&opcion);
        switch(opcion)
        {
        case 1:
            pasarDeArbolToADFinOrden(arbol,arreglo);
            break;
        case 2:
            pasarDeArbolToADFpreOrden(arbol,arreglo);
            break;
        case 3:
            pasarDeArbolToADFpostOrden(arbol,arreglo);
            break;
        default:
            printf("No existe , reintente\n");
            break;
        }
}
nodoArbol *BorrarNodoArbol (nodoArbol * arbol,int idDelArbol)
{
    if(arbol!=NULL)
    {
        if(idDelArbol==arbol->p.id)
        {

            if(arbol->izquierdo!=NULL)
            {

                nodoArbol * aux = NodoMasDerecho(arbol->izquierdo);//LE ASIGNA A AUX EL MAS PARECIDO
                arbol->p.id=aux->p.id;//EL MAS PARECIDO AHORA ES LA NUEVA RAIZ
                arbol->izquierdo=BorrarNodoArbol(arbol->izquierdo, aux->p.id);// LLAMA A LA RECURSIVIDAD

            }
            else if(arbol->derecha!=NULL)
            {

                nodoArbol * aux2 =NodoMasIzquierdo(arbol->derecha);
                arbol->p.id=aux2->p.id;
                arbol->derecha=BorrarNodoArbol(arbol->derecha, aux2->p.id);
            }
            else
            {
                free(arbol);
                arbol=NULL;
            }
        }


            else if(idDelArbol > arbol->p.id)
            {
                arbol->derecha=BorrarNodoArbol(arbol->derecha, idDelArbol);
            }
            else
            {
                arbol->izquierdo=BorrarNodoArbol(arbol->izquierdo,idDelArbol);
            }
        }

    return arbol;
}

nodoArbol *NodoMasDerecho(nodoArbol *arbol)//RETORNA EL MAS CERCANO A LA LINEA DE LA RAIZ DE LA PARTE DERECHA DEL ARBOL
{
/// si arbol es distinto de null llamo a la recursividad sino retorno el arbol;

    nodoArbol * aux=NULL;

    if(arbol->derecha==NULL)
    {
        aux=arbol;
    }
    else
    {
        aux=NodoMasDerecho(arbol->derecha);
    }

    return aux;

}

nodoArbol *NodoMasIzquierdo(nodoArbol *arbol)//RETORNA EL MAS CERCANO A LA LINEA DE LA RAIZ DE LA PARTE IZQUIERDA DEL ARBOL
{

    nodoArbol * aux=NULL;

    if(arbol->izquierdo==NULL)
    {
        aux=arbol;
    }
    else
    {

        aux=NodoMasIzquierdo(arbol->izquierdo);
    }

    return aux;
}
