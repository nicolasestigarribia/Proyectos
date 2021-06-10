#include "Estructuras.h"

int main()
{
    int i=0;
    nodoArbol *arbol;
    arbol=inicArbol();
    char archivousuario[]="archivoUsuarios.dat";
    char archivoPersona[]="archivoPersona.dat";

    caja arreglo[12];
    int resp;

    ///FUNCION DE INGRESO AL SISTEMA

/*
    printf("\t\t /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\ \n");
    puts("\t\t /\/\/\/\                  /\/\/\/\ ");
    puts("\t\t /\/\/\/\    BIENVENIDO    /\/\/\/\ ");
    puts("\t\t /\/\/\/\                  /\/\/\/\ ");
    printf("\t\t /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\ \n\n");
    i=Login(archivousuario);
    system("cls");
*/

if(i==0){
    do
    {
    system("cls");
    printf("\t\t /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\ \n");
    puts("\t\t /\/\/\ "); puts("\t\t    BIENVENIDO AL SISTEMA ");
    puts("\t\t\t\t\t /\/\/\ ");
    printf("\t\t /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\ \n\n");
        printf("\n1->AMBL DE USUARIOS.\n\n2->AMBL DE PERSONAS.\n\n3->ARBOL.\n\n4->GO TO SUPERMARKET.\n\n5->SALIR DEL SISTEMA.\n\nINGRESE LA OPCION DESEADA:");
        fflush(stdin);
        scanf("%i",&resp);
        switch(resp)
        {
        case 1:
            system("cls");
            funcionMenuUsuarios(archivousuario);/// YA ESTA
            system("cls");
            break;
        case 2:
            system("cls");
            menuPersonas(archivoPersona); /// YA ESTA
            system("cls");
            break;
        case 3:
            system("cls");
            arbol=menuArbol(arreglo,archivoPersona,arbol); /// YA ESTA
            break;
        case 4:
            system("cls");
            menuSupermecado(arreglo); /// YA ESTA
            //system("cls");
            break;
        case 5:
            system("cls");
            printf("\t\tHASTA LUEGO.\n\n");
            break;
        default:
            system("cls");
            printf("\nOPCION INCORRECTA");
            break;
        }
    }
    while(resp!=5);
    return 0;
 }else{

    printf("\nLogeo Erroneo, Chau chau chauuu..\n\n");

 }
}
