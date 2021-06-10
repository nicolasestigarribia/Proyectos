#include "Estructuras.h"


void inicFila(Fila *fila)
{
    fila->inicio=iniclista();
    fila->ultimo=iniclista();
}
void agregarEnFilaAlFnal(Fila *fila,persona people)
{
    nodo *aux=crearNodo(people);
    if(fila->inicio==NULL)
    {
        fila->inicio=aux;
        fila->ultimo=aux;
    }
    else
    {
        fila->inicio=agregarAlFinal(fila->inicio,aux);
        fila->ultimo=aux;
    }

}
void agregarEnFilaSTRF(Fila *fila,persona people)
{
    nodo *aux=crearNodo(people);
    if(fila->inicio==NULL)
    {
        fila->inicio=aux;
        fila->ultimo=aux;
    }
    else
    {
        fila->inicio=agregarEnOrdenCantidadArticulos(fila->inicio,aux);
        fila->ultimo=aux;
    }
}
void agregarEnFilaPrioridadApropiativo(Fila *fila,persona people)
{
    nodo *aux=crearNodo(people);
    if(fila->inicio==NULL)
    {
        fila->inicio=aux;
        fila->ultimo=aux;
    }
    else
    {
        fila->inicio=agregarEnOrdenTipoCliente(fila->inicio,aux);
        fila->ultimo=aux;
    }
}
void quitarDeFila(Fila *fila)
{
    if(fila->inicio!=NULL)
    {
        nodo *aux=fila->inicio;
        nodo *sig=aux->siguiente;
        if(fila->inicio!=fila->ultimo)
        {
            sig->anterior=NULL;
            fila->inicio=sig;
        }
        else
        {
            fila->inicio=NULL;
            fila->ultimo=NULL;
        }
        free(aux);
    }
}
void mostrarFila(Fila *fila)
{
    if(fila->inicio==NULL)
    {
        printf("\nLA FILA ESTA VACIA.");
    }
    else
    {
        mostrarLista(fila->inicio);
    }
}
int filavacia(Fila *fila)
{
    int retorno=1;
    if(fila->inicio==NULL)
    {
        retorno=0;
    }
    return retorno;
}
int contarCantFila(Fila *filita)
{
    int rsta=0;
    nodo *aux=filita->inicio;
    while(aux!=NULL)
    {
        rsta++;
        aux=aux->siguiente;
    }
    return rsta;
}
void agregarEnTiempoSJF(Fila* filita,persona people,int pos)
{
    int SumaCantArt=0;
    nodo *aux=crearNodo(people);
    if(filita->inicio==NULL)
    {
        filita->inicio=aux;
        filita->ultimo=aux;
    }
    else
    {
        nodo *ante=filita->inicio;
        while(ante!=NULL && SumaCantArt<pos)
        {
            SumaCantArt=SumaCantArt+ante->cliente.cantArticulos;
            ante=ante->siguiente;
        }
        ante=ante->anterior;
        ante=agregarAlPrincipio(ante,aux);
    }
}

Fila *agregarEnTiempoSTRF(Fila *filita,persona people,int pos)
{
    int SumaCantArt=0;
    int corte=0;
    nodo *aux=crearNodo(people);
    if(filita==NULL)
    {
        filita->inicio=aux;
        filita->ultimo=aux;
    }
    else
    {
        nodo *ante=filita->inicio;

        while(ante!=NULL && SumaCantArt<pos && corte==0)
        {
            SumaCantArt=SumaCantArt+ante->cliente.cantArticulos;
            ante=ante->siguiente;
            if((SumaCantArt+ante->cliente.cantArticulos)>pos){
                SumaCantArt=SumaCantArt-pos;
                corte=1;
            }
        }
        ante=ante->anterior;
        ante->siguiente=agregarEnOrdenCantidadArticulos(ante,aux);
    }
    return filita;
}

Fila *agregarEnTiempoPrioridadApropia(Fila *filita,persona people,int pos)
{
    int SumaCantArt=0;
    int corte=0;
    nodo *aux=crearNodo(people);
    if(filita==NULL)
    {
        filita->inicio=aux;
        filita->ultimo=aux;
    }
    else
    {
        nodo *ante=filita->inicio;
        while(ante!=NULL && SumaCantArt<pos && corte==0)
        {
            SumaCantArt=SumaCantArt+ante->cliente.cantArticulos;
            ante=ante->siguiente;
            if((SumaCantArt+ante->cliente.cantArticulos)>pos){
                SumaCantArt=SumaCantArt-pos;
                corte=1;
            }
        }
        ante=ante->anterior;
        ante->siguiente=agregarEnOrdenCantidadArticulos(ante,aux);
    }
    return filita;
}

