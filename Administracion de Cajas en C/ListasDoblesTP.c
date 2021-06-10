#include "Estructuras.h"

nodo *iniclista()
{
    return NULL;
}
nodo *crearNodo(persona cliente)
{
    nodo *aux=(nodo*)malloc(sizeof(nodo));
    aux->cliente=cliente;
    aux->anterior=NULL;
    aux->siguiente=NULL;
    return aux;
}
nodo *agregarAlPrincipio(nodo *lista,nodo *nuevo)
{
    if(lista==NULL)
    {
        lista=nuevo;

    }else
    {
        lista->anterior=nuevo;
    }
    return nuevo;
}
nodo *agregarAlFinal(nodo *lista,nodo *nuevo)
{
    if(lista!=NULL)
    {
        nodo *aux=buscarUltimo(lista);
        aux->siguiente=nuevo;
        nuevo->anterior=aux;
    }
    return lista;
}
nodo *buscarUltimo(nodo *lista)
{
    nodo *aux=lista;
    while(aux->siguiente!=NULL)
    {
        aux=aux->siguiente;
    }
    return aux;
}
nodo *agregarEnOrdenTipoCliente(nodo *lista, nodo *nuevo)
{
    if(lista==NULL)
    {
        lista=nuevo;
    }
    else
    {
        if(nuevo->cliente.tipoCliente<lista->cliente.tipoCliente)
        {
            lista=agregarAlPrincipio(lista,nuevo);
        }
        else
        {
            nodo *ante=lista;
            nodo *seg=lista->siguiente;
            while(nuevo->cliente.tipoCliente>=ante->cliente.tipoCliente && seg!=NULL)
            {
                ante=seg;
                seg=seg->siguiente;
            }
            ante->siguiente=nuevo;
            nuevo->anterior=ante;
            nuevo->siguiente=seg;
            if(seg!=NULL)
            {
                seg->anterior=nuevo;
            }
        }
    }
    return lista;
}
nodo *agregarEnOrdenCantidadArticulos(nodo *lista,nodo *nuevo)
{
    if(lista==NULL)
    {
        lista=nuevo;
    }
    if(nuevo->cliente.cantArticulos<lista->cliente.cantArticulos)
    {
        lista=agregarAlPrincipio(lista,nuevo);
    }
    else
    {
        nodo *ante=lista;
        nodo *seg=lista->siguiente;
        while(seg!=NULL && nuevo->cliente.cantArticulos>=seg->cliente.cantArticulos)
        {
            ante=seg;
            seg=seg->siguiente;
        }
        ante->siguiente=nuevo;
        nuevo->anterior=ante;
        nuevo->siguiente=seg;
        if(seg!=NULL)
        {
            seg->anterior=nuevo;
        }
    }
    return lista;
}
void UnNodo(nodo *aux)
{
    printf("\n Identificacion Personal: %d \n",aux->cliente.id);
    printf("\n Nombre:   %s \n",aux->cliente.nombres);
    printf("\n Apellido: %s \n",aux->cliente.apellido);
    if(aux->cliente.tipoCliente==1)
        printf("\n Cliente: Embarazada \n");
    if(aux->cliente.tipoCliente==2)
        printf("\n Cliente: Jubilado  \n");
    if(aux->cliente.tipoCliente==3)
        printf("\n Cliente: Comun  \n");
    if(aux->cliente.medioPago==1)
        printf("\n Medio de pago: Efectivo  \n");
    if(aux->cliente.medioPago==2)
        printf("\nMedio de pago: Credito  \n");
    if(aux->cliente.medioPago==3)
        printf("\n Medio de pago: Todos  \n");

    printf("\nTiempo procesado: %d",aux->cliente.tiempoProcesado);
    printf("\nTiempo de espera: %d",aux->cliente.tiempoEspera);
    printf("\n Cantidad de articulos: %d \n",aux->cliente.cantArticulos);
    if(aux->cliente.eliminado==1)
        printf("Este usuario ha sido eliminado:\n");
    if(aux->cliente.eliminado==0)
        printf("Persona Activa.\n");
    printf("---------------------------------------\n");
}
void mostrarLista(nodo * lista)
{
    nodo *aux=lista;
    while(aux!=NULL)
    {
        UnNodo(aux);
        aux=aux->siguiente;
    }
}
nodo *borrarNodoPorNombre(nodo *lista)
{
    char nombre[20];
    nodo *aux;
    printf("\nIngrese el nombre de la persona que desea eliminar: ");
    fflush(stdin);
    scanf("%s",&nombre);

    if(lista!=NULL && nombre==lista->cliente.nombres)
    {
        aux=lista;
        lista=lista->siguiente;
        free(aux);
    }
    else
    {
        nodo *ante=lista;
        nodo *seg=lista->siguiente;
        while(seg!=NULL && ante->cliente.nombres!=nombre)
        {
            ante=seg;
            seg=seg->siguiente;
        }
        aux=ante;
        ante=ante->anterior;
        ante->siguiente=seg;
        free(aux);
        if(seg!=NULL)
        {
            seg->anterior=ante;
        }
    }
    return lista;
}
nodo *borrarNodoPrimero(nodo *lista)
{
    if(lista!=NULL)
    {
        nodo*aux=lista;
        lista=lista->siguiente;
        free(aux);
    }
    return lista;
}
