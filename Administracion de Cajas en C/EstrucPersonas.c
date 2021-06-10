#include "Estructuras.h"

int CuentaLosId(char archivoPersona[])
{
    FILE *archivo;
    int i=0;
    archivo=fopen(archivoPersona,"rb");
    persona a;
    while(fread(&a,sizeof(persona),1,archivo)>0)// MIENTRAS TENGA DATOS
    {
        i++; //INCREMENTO EL CONTADOR
    }
    //LLEGO AL FINAL Y LA VARIABRE I TIENE LA CANTIDAD DE PERSONAS
    fclose(archivo);
    return i;//RETORNA LA CANTIDAD DE PERSONAS

}

void cargoPersona(char archivoPersona[])
{
    FILE *archivo;
    char control='s';
    persona a;
    int i=CuentaLosId(archivoPersona);
    archivo=fopen(archivoPersona,"ab");
    if(archivo!=NULL)
    {
        while(control=='s')
        {
            i++;//SUMA 1 MAS LOS ANTERIORES, EL USUARIO NO PUEDE ELEGIR SU ID
            a.id=i;
            printf("\n\t    REGISTRO CLIENTE  ");
            printf("\n\t========================\n\n");
            printf("Su Identificacion Personal(I.D):%d\n",i);
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
            }while(a.tipoCliente<1 || a.tipoCliente>3);
            do
            {
                    printf("\nIngrese medio de pago: \n 1->Efectivo \n 2->Credito \n 3->Todos \n\n-->>OPCION:  ");
                scanf("%d",&a.medioPago);
            }while(a.medioPago<1 || a.medioPago>3);
            printf("\nCantidad de articulos: ");
            scanf("%d",&a.cantArticulos);
            a.eliminado=0;
            a.tiempoEspera=0;
            a.tiempoProcesado=0;
            fwrite(&a,sizeof(persona),1,archivo);
            printf("\nDesea continuar S/N ?   ");
            fflush(stdin);
            scanf("%c",&control);
        }
    }
    fclose(archivo);
}
void muestro1Persona(char archivoPersona[],int id)
{
    FILE *archivo;
    persona a;
    archivo=fopen(archivoPersona,"rb");
    if(archivo!=NULL)
    {
        while(fread(&a,sizeof(persona),1,archivo)>0)
        {
            if(a.id==id)
            {
                printea(a);
            }
        }
    }
}

void muestroPersona(char archivoPersona[])
{
    FILE *archivo;
    persona a;
    archivo=fopen(archivoPersona,"rb");
    if(archivo!=NULL)
    {
        //ESTO IRIA EN EL MENU
        while(fread(&a,sizeof(persona),1,archivo)>0)
        {
            printea(a);
        }
    }
    fclose(archivo);
}

int buscaPersona(char archivoPersona[],int id)
{
    //BUSCA SI EL ID INGRESADOS ES CORRECTO
    FILE *archivo;
    archivo=fopen(archivoPersona,"rb");
    persona a;
    int v=0;
    while(fread(&a,sizeof(persona),1,archivo)>0)
    {
        if(a.id==id)
        {
            v=1;
        }

    }
    fclose(archivo);
    return v;//RETORNA 1 SI LO ENCONTRO
}
void bajaPersona(char archivoPersona[],int id)
{
    FILE *archivo;
    persona a;
    archivo=fopen(archivoPersona, "r+b");

    int v=buscaPersona(archivoPersona,id);
    while(v!=1)// EXISTE EL ID?
    {
        printf("Id erroneo. Reintente...\n");
        scanf("%d",&id);
        v=buscaPersona(archivoPersona,id);
    }

    if(archivo!=NULL)
    {

        fseek(archivo,(id-1)*sizeof(persona),0);///SE PARA EN EL ANTERIOR
        fread(&a, sizeof(persona),1,archivo);/// LO LEE
        a.eliminado=1; /// ELIMINA

        fseek(archivo,(id-1)*sizeof(persona),0);///SE PARA EN EL ANTERIOR
        fwrite(&a, sizeof(persona),1,archivo);///LO ESCRIBE AL SIGUIENTE PISANDO EL DATO
        printf("\n\nUsuario eliminado con exito \n");

    }

    fclose(archivo);
}
void printeaElmodifica()
{
    printf("\n INGRESE CAMPO A MODIFICAR :  \n");
    printf("\n1- Nombre \n");
    printf("\n2- Apellido\n");
    printf("\n3- Tipo De Cliente\n");
    printf("\n4- Tipo De Pago\n");
    printf("\n5- Cantidad De Articulos\n");
    printf("\n6- Usuario activo/Inactivo\n");
}

void modificacionPersona (char archivoPersona[],int id)
{
    FILE *archivo;
    archivo=fopen(archivoPersona, "r+b");

    persona a;
    int opcion=0;
    char control='s';
    int v=buscaPersona(archivoPersona,id);

    while(v!=1)
    {
        printf("Id erroneo. Reintente...\n");
        scanf("%d",&id);
        v=buscaPersona(archivoPersona,id);
    }

    if(archivo!=NULL)
    {
        fseek(archivo, sizeof(persona)*(id-1),0);
        fread(&a, sizeof(persona),1,archivo);
        while(control=='s')
        {
            printeaElmodifica();
            printf("\n-->>OPCION:  ");
            scanf(" %d", &opcion);
            switch(opcion)
            {
            case 1:
                printf("Ingrese nuevo nombre:   \n");
                fflush(stdin);
                gets(a.nombres);
                break;
            case 2:
                printf("Ingrese su nuevo apellido:   \n");
                fflush(stdin);
                gets(a.apellido);

                break;
            case 3:
                printf("\n Tipo de cliente :\n 1_Embarazada \n 2_Jubilado \n 3_Cliente comun \n");
                scanf("%d",&a.tipoCliente);
                break;
            case 4:
                printf("\nIngrese su medio de pago: \n 1_Efectivo \n 2_Credito \n 3_Todos \n");
                scanf("%d",&a.medioPago);
                break;

            case 5:

                printf("Ingrese la cantidad de articulos:   \n");
                scanf("%d",&a.cantArticulos);
                break;
            case 6:

                printf("\n 1= ELIMINAR  ///  0= ACTIVAR  \n");
                printf("\n-->>OPCION:  ");
                scanf("%d",&a.eliminado);
                break;
            default:
                printf("La opcion no existe, reintente..\n");
                scanf("%d",&opcion);
            }
            printf("Desea continuar s/n\n");
            fflush(stdin);
            scanf("%c",&control);
        }

        fseek(archivo, sizeof(persona)*(-1),1);
        fwrite(&a, sizeof(persona),1,archivo);

    }

    fclose(archivo);
}
void menuPersonas(char archivoPersona[])
{
    int id;
    int opcion;
    do
    {
        system("cls");
            //printf("\n\t          ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        printf("\n                  BIENVENIDO AL MENU CLIENTES");
        printf("\n\t         ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n\n");
        printf("\n 1)CARGAR UN CLIENTE.\n\n 2)MOSTRAR CLIENTES.\n\n 3)ELIMINAR UN CLIENTE.\n\n 4)MODIFICAR CLIENTE.\n\n 5)VOLVER.\n\n INGRESE OPCION DESEADA--> ");
        scanf("%d",&opcion);
        switch(opcion)
        {
        case 1:
            system("cls");
            cargoPersona(archivoPersona);
            system("cls");
            break;
        case 2:
            system("cls");
            muestroPersona(archivoPersona);
            system("pause");
            break;
        case 3:
            system("cls");
            //muestroPersona(archivoPersona);
            printf("\n                  ELIMINAR CLIENTE");
                printf("\n\t         ~~~~~~~~~~~~~~~~~~~~\n\n");
            printf(" ID DEL CLIENTE A ELIMINAR:  ");
            scanf("%d",&id);
            bajaPersona(archivoPersona, id);
            muestro1Persona(archivoPersona,id);
            break;
        case 4:
            system("cls");
            //muestroPersona(archivoPersona);
            printf("\n                  MODIFICAR CLIENTE");
                printf("\n\t         ~~~~~~~~~~~~~~~~~~~~\n\n");
            printf(" ID DEL CLIENTE A MODIFICAR:  ");
            scanf("%d",&id);
            modificacionPersona(archivoPersona,id);
            muestro1Persona(archivoPersona,id);
            break;
        default:
            system("cls");
            printf("\nOPCION INCORRECTA, INTENTE NUEVAMENTE: ");
            break;
        }
    }
    while(opcion!=5);
}
