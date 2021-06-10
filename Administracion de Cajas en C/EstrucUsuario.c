#include "Estructuras.h"

const int MatTes[2][2]= {{2,1},{-1,3}};
const char ABC[]= {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
const int MaxFilas = 2;
const int MaxArreglo = 10;
const int MaxColumnas = 5;
void funcionMenuUsuarios(char archivousuario[])
{

    int aux;
    do
    {
        printf("\n\t      =================================\n");
        printf("\t      == BIENVENIDO MENU DE USUARIOS ==\n" );
        printf("\t      ================================= \n\n");
        printf("\n1DAR DE ALTA UN USUARIO.\n\n2MODIFICAR UN USUARIO.\n\n3ELIMINAR UN USUARIO.\n\n4MOSTRAR USUARIOS.\n\n5VOLVER.\n\nINGRESE OPCION DESEADA: ");
        fflush(stdin);
        scanf("%i",&aux);
        switch(aux)
        {
        case 1:
            system("cls");
            cargarMuchos(archivousuario);
            system("cls");
            break;
        case 2:
            system("cls");
            //mostrarClientes(archivousuario);
            modificacionMayor(archivousuario);
            system("cls");
            break;
        case 3:
            system("cls");
            //mostrarClientes(archivousuario);
            eliminarusuario(archivousuario);
            system("cls");
            printf("\nUSUARIO ELIMINADO CON EXITO.\n\n");
            break;
        case 4:
            system("cls");
            mostrarClientes(archivousuario);
            break;
        case 5:
            break;
        default:
            system("cls");
            printf("\nOPCION INCORRECTA, INTENTE NUEVAMENTE.\n\n");
        }
    }
    while(aux!=5);
}
void cargarUsuario(char ArchivoUsuario[])
{

    char contra[10];
    FILE *archi;
    archi=fopen(ArchivoUsuario,"ab");
    Usuario aux;
    printf("\t--->REGISTRO USUARIO <----  ");
    printf("\n\nIngrese el nombre: ");
    fflush(stdin);
    gets(aux.nombre);
    printf("\nIngrese el apellido: ");
    fflush(stdin);
    gets(aux.apellido);
    int id=CalculaId(ArchivoUsuario);
    id++;
    aux.id=id;
    printf("\nIngrese nombre de usuario: ");
    fflush(stdin);
    gets(aux.usuario);
    int veri;
    do
    {
        veri=VerificarUser(ArchivoUsuario,aux);
        if(veri==1)
        {
            printf("El usuario ya existe, ingrese otro : ");
            fflush(stdin);
            gets(aux.usuario);
        }
    }
    while(veri==1);
    printf("\nIngrese contrasena: ");
    fflush(stdin);
    gets(contra);
    Encriptacion(contra,aux.pas);
    aux.eliminado=0;///cero significa activo, 1 significa eliminado(inactivo)
    fwrite(&aux,sizeof(Usuario),1,archi);
    fclose(archi);
}
int CalculaId(char nombrearchivo[])
{
    int i=0;
    FILE *archi;
    Usuario p;
    archi=fopen(nombrearchivo,"rb");
    while(fread(&p,sizeof(Usuario),1,archi)>0)
    {
        i++;
    }
    return i;
    fclose(archi);
}
void cargarMuchos(char archivousuario[])
{
    char control='s';
    int i=0;
    while(control=='s' && i<30)
    {
        cargarUsuario(archivousuario);
        i++;
        printf("\nDesea ingresar otro usuario? s/n: ");
        fflush(stdin);
        scanf("%c",&control);
    }
}
int VerificarUser (char nombre_archivo[], Usuario a)
{

    FILE * archivo;
    archivo = fopen(nombre_archivo, "rb");
    Usuario verificacion;
    int flag=0;
    int veri = 0;

    if (archivo!=NULL)
    {
        while (fread(&verificacion, sizeof(Usuario),1,archivo)>0 && flag==0)
        {

            veri = strcmp (verificacion.usuario, a.usuario);
            if (veri==0)
            {
                flag=1;
            }
        }
        fclose(archivo);

    }
    return flag;

}
void Encriptacion (char contrasena[],int matriz[][MaxColumnas])///FUNCION QUE JUNTA TODAS LAS ENCARGADAS DE ENCRIPTAR
{
    PasarAMatriz(matriz, contrasena);
    MultiplicaMatrices(matriz);
}
void PasarAMatriz (int matriz[][MaxColumnas], char arreglo [])
{
    ///Pasar contrase�a numerica de arreglo a matriz
    int validos=0;
    validos=strlen(arreglo);
    int f=0;
    int c=0;
    int s=0;
    for (f=0; f<MaxFilas; f++)
    {
        for (c=0; c<MaxColumnas; c++)
        {
            if(s<validos)
            {
                matriz[f][c]= CodigoLetra(arreglo[s]);

                s++;
            }
            else
            {
                matriz[f][c]=0; ///espacios en blanco se igualan a cero
            }
        }
    }
    ///MostrarMatriz("PASS ORIGINAL\n", matriz);
}
int CodigoLetra(char letra)
{
    int posicion=0, indice=0;
    int largoArreglo =strlen(ABC);

    while(indice < largoArreglo && posicion == 0)
    {
        if(letra==ABC[indice])
        {
            ///guardo contrase�a numerica en arreglo aux
            posicion = indice + 1;
        }
        indice++;
    }

    return posicion;
}

void MultiplicaMatrices (int matriz [][MaxColumnas])///RECIBE MATRIZ NUMERICA
{
    ///FUNCION QUE ENCRIPTA LA CONTRASENA
    int f=0;
    int c=0;
    int passencript[2][5];

    for (f=0; f<MaxFilas; f++)
    {
        for (c=0; c<MaxColumnas; c++)
        {

            passencript[f][c]=(MatTes[f][0]*matriz[0][c])+ (MatTes[f][1]* matriz[1][c]);
        }
    }
    ///MostrarMatriz("Matriz encriptada\n", passencript);

    for(f=0; f<MaxFilas; f++)
    {
        for (c=0; c<MaxColumnas; c++)/// PASA LA MATRIZ ENCRIPTADA A LA ESTRUCTURA
        {
            matriz[f][c]= passencript[f][c];
        }
        printf("\n");
    }
    ///MostrarMatrizFloat("Matriz encriptada\n", matriz);

}

void Cliente(Usuario a)///ESTA FUNCION LE FALTA MOSTRAR LA CONTRASE�A DESENCRIPTADA.
{
    puts("\n======================================");
    printf("\n ID: %d\n", a.id);
    printf("\n Nombre : %s %s\n ",a.nombre,a.apellido);
    printf("\n Usuario : %s\n ",a.usuario);
    puts("\n======================================");
}



void mostrarClientes(char nombreArch[])///MUESTRAS LOS CLIENTES REGISTRADOS QUE ESTEN ACTIVOS
{
    FILE *archi;
    Usuario varios;
    int cliente=1;
    archi=fopen(nombreArch,"rb");
    if(archi!=NULL)
    {
        while(fread(&varios,sizeof(Usuario),1,archi)>0)
        {
            if(varios.eliminado==0)
            {
                Cliente(varios);
            }
        }
    }
    else
    {
        printf("\nNo hay usuarios ingresados.");
    }
    fclose(archi);
}
void MostrarMatriz(char titulo[], int matriz[][MaxColumnas])
{
    printf(titulo);
    int f=0;
    int c=0;
    for (f=0; f<MaxFilas; f++)
    {
        for (c=0; c<MaxColumnas; c++)
        {
            printf("|%d|", matriz[f][c]);
        }
        printf("\n");
    }
}

       /// Desecriptacion    ///////////////

void MatrizInversa (float inversa[][2])
{
    int f=0;
    int c=0;
    int determinante=0;
    float Adjunta [2][2];


    determinante= (MatTes[0][0]*MatTes[1][1]-MatTes[0][1]*MatTes[1][0]);

    Adjunta[f][c] = MatTes [f+1][c+1];
    Adjunta[f][c+1] =  MatTes[f+1][c];
    Adjunta [f+1][c] =  MatTes[f][c+1];
    Adjunta [f+1][c+1] = MatTes[f][c];

    //MostrarMatriz2X2("MATRIZ ADJUNTA\n", Adjunta);


    for (f=0; f<2; f++)
    {
        for (c=0; c<2; c++)
        {
            inversa[f][c]=((float)Adjunta[f][c] / (float)determinante);
        }
        printf("\n");
    }
    ///MostrarMatriz2X2("Matriz Inversa\n", inversa);
}



void DesencripPass (int matriz[][MaxColumnas], char password[MaxArreglo])  ///Desencripta la pass
{

    float inversa[2][2];
    MatrizInversa(inversa);
    float passDescrip [2][5]= {0};
    int b=0;
    int f=0;
    int c=0;
    ///MostrarMatriz2X2("Matriz inversa\n", inversa);
    ///MostrarMatrizInt("Pass encriptada\n", matriz);
    int co=0;
    float calculo = 0;
    for (f=0; f<2; f++)
    {
        for (c=0; c<5; c++)
        {
            calculo = matriz[0][c]*(float)inversa[f][0] + matriz[1][c]*(float)inversa[f][1];
            ///passDescrip[f][c]=(float)matriz[0][c]*(float)inversa[f][0]+(float)matriz[1][c]*(float)inversa[f][1];//MULTIPLICA MATRIZ ENCRIPTADA POR LA INVERSA Y SE DESENCRIPTA
            passDescrip[f][c]=calculo;
        }
    }
    //MostrarMatrizFloat("DESENCRIPTADA\n", passDescrip);
    int validosPass=0;
    DecodificaPosiciones(passDescrip, password);
    validosPass=strlen(password);
    password[validosPass]='\0';
    //MostrarArregloPass(password);
}
void MostrarArregloPass(char arreglo[])
{
    int indice=0;
    for (indice=0; indice < MaxArreglo; indice++)
    {
        printf("|%c", arreglo[indice]);
    }
    printf("\n");
}
void DecodificaPosiciones(float passDescrip [][MaxColumnas], char password[])
{
    float uno = 1;
    int f=0;
    int c=0;
    int indice =0;
    int letraPos = 0;
    float passPos = 0;

    for (f=0; f<MaxFilas; f++)
    {
        for (c=0; c<MaxColumnas; c++)
        {
            //printf("pass: %f -->", passDescrip[f][c]);
            passPos = passDescrip[f][c];
            //printf("pos-1: %f -->", passPos--);
            letraPos = --passPos;
            //printf("letraPos: %d \n", letraPos);
            password[indice] = ABC[letraPos];
            indice++;
        }
    }
}
void MostrarMatriz2X2 (char titulo[], float matriz[][2])
{

    printf(titulo);
    int f=0;
    int c=0;
    for (f=0; f<2; f++)
    {
        for (c=0; c<2; c++)
        {
            printf("|%.2f|", matriz[f][c]);
        }
        printf("\n");
    }

}
void MostrarMatrizFloat(char titulo[], float matriz[][MaxColumnas])   ///MUESTRA MATRIZ DE 2X5
{
    printf(titulo);
    int f=0;
    int c=0;
    for (f=0; f<MaxFilas; f++)
    {
        for (c=0; c<MaxColumnas; c++)
        {
            printf("|%f|", matriz[f][c]);
        }
        printf("\n");
    }
}
int corroborarId(char nombreusuario[],int aux)///CORROBORA SI EL ID POR PARAMETRO
{
    FILE *archi;
    archi=fopen(nombreusuario,"rb");
    int resp=0;
    Usuario p;
    while(fread(&p,sizeof(Usuario),1,archi)>0 && resp==0)
    {
        if(p.id==aux)
        {
            resp=1;
        }
    }
    fclose(archi);
    return resp;
}
///MODIFICACION DE USUARIO
void modificacionMayor(char archivousuario[])
{
    char contra[10];
    int resp;
    int numero;
    printf("\t-----> MODIFICO USUARIO   \n");
    printf("\nIngrese ID del Cliente: ");
    fflush(stdin);
    scanf("%d",&numero);
    int identificacion;
    identificacion=corroborarId(archivousuario,numero);
    while(identificacion==0)
    {
        printf("\nNo existe usuario con ese id, visualiza el cuadro anterior e ingrese el numero de usuario que desea: ");
        fflush(stdin);
        scanf("%d",&numero);
        identificacion=corroborarId(archivousuario,numero);
    }
    do///MENU DENTRO DE UN DO WHILE QUE SE EJECUTA HASTA QUE EL USUARIO DESEA SALIR DEL MENU.
    {
        system("cls");
        printf("\t-----> MODIFICO CLIENTES   \n");
        printf("\n\n1<= Modificar nombre\n\n2<= Modificar apellido\n\n3<= Modificar usuario\n\n4<= Modificar contrasena\n\n5<= Volver.\n\nIngrese la opcion que desea: ");
        fflush(stdin);
        scanf("%i",&resp);
        switch(resp)
        {
        case 1:
            system("cls");
            supmodificacionombre(numero,archivousuario);
            break;
        case 2:
            system("cls");
            submodificacionapellido(numero,archivousuario);
            break;
        case 3:
            system("cls");
            submodificacionusuario(numero,archivousuario);
            break;
        case 4:
            system("cls");
            submodificacioncontrasenia(numero,archivousuario);
            break;
        case 5:
            break;
        }
    }
    while(resp!=5);
}
void supmodificacionombre(int numero,char archivousuario[])
{
    Usuario aux;
    FILE *archi;
    archi=fopen(archivousuario,"r+b");
    fseek(archi,sizeof(Usuario)*(numero-1),0);
    fread(&aux,sizeof(Usuario),1,archi);
    printf("\n->Ingrese el nuevo nombre: ");
    fflush(stdin);
    scanf("%s",&aux.nombre);
    fseek(archi,sizeof(Usuario)*(-1),SEEK_CUR);
    fwrite(&aux,sizeof(Usuario),1,archi);
    fclose(archi);
}
void submodificacionapellido(int numero, char archivousuario[])
{
    Usuario aux;
    FILE *archi;
    archi=fopen(archivousuario,"r+b");
    fseek(archi,sizeof(Usuario)*(numero-1),0);
    fread(&aux,sizeof(Usuario),1,archi);
    printf("\n->Ingrese el nuevo apellido: ");
    fflush(stdin);
    scanf("%s",&aux.apellido);
    fseek(archi,sizeof(Usuario)*(-1),SEEK_CUR);
    fwrite(&aux,sizeof(Usuario),1,archi);
    fclose(archi);
}
void submodificacionusuario(int numero,char archivousuario[])
{
    Usuario aux;
    FILE *archi;
    archi=fopen(archivousuario,"r+b");
    fseek(archi,sizeof(Usuario)*(numero-1),0);
    fread(&aux,sizeof(Usuario),1,archi);
    printf("\n->Ingrese el nuevo usuario: ");
    fflush(stdin);
    scanf("%s",&aux.usuario);
    fseek(archi,sizeof(Usuario)*(-1),SEEK_CUR);
    fwrite(&aux,sizeof(Usuario),1,archi);
    fclose(archi);
}
void submodificacioncontrasenia(int numero,char archivousuario[])
{
    char contrasenia[20];
    Usuario aux;
    FILE *archi;
    archi=fopen(archivousuario,"r+b");
    fseek(archi,sizeof(Usuario)*(numero-1),0);
    fread(&aux,sizeof(Usuario),1,archi);
    printf("\n->Ingrese la nuevo contrasena: ");
    fflush(stdin);
    gets(contrasenia);
    Encriptacion(contrasenia,aux.pas);
    fseek(archi,sizeof(Usuario)*(-1),SEEK_CUR);
    fwrite(&aux,sizeof(Usuario),1,archi);
    fclose(archi);
}
void eliminarusuario(char archivousuario[])
{
    int identi;
    int numero;
    Usuario aux;
    FILE *archi;
    archi=fopen(archivousuario,"r+b");
    printf("\nIngrese el id del usuario que desea eliminar: ");
    fflush(stdin);
    scanf("%i",&numero);
    identi=corroborarId(archivousuario,numero);
    while(identi==0)
    {
        printf("\nNo existe usuario con ese id, visualiza el cuadro anterior e ingrese el numero de usuario que desea: ");
        fflush(stdin);
        scanf("%d",&numero);
        identi=corroborarId(archivousuario,numero);
    }
    fseek(archi,sizeof(Usuario)*(numero-1),0);
    fread(&aux,sizeof(Usuario),1,archi);
    aux.eliminado=1;
    fseek(archi,sizeof(Usuario)*(-1),SEEK_CUR);
    fwrite(&aux,sizeof(Usuario),1,archi);
    fclose(archi);
}

int Login(char nombre_archivo[])
{

    FILE *archivo;
    archivo=fopen(nombre_archivo,"rb");
    Usuario a;

    char contrasena[]= {0};
    int contador=0;
    int z=0;
    int j=0;
    int h=3;
    int validos=0;
    int validos1=0;
    char pass[10]= {0};
    char user[10]= {0};

    do
    {
        printf("\t\t* Usuario: ");
        fflush(stdin);
        gets(user);

        printf("\n\t\t* Contrasena: ");
        fflush(stdin);
        gets(pass);

        fseek(archivo, 0, 0);

            while(!feof(archivo) && j==0)
            {
                fread(&a,sizeof(Usuario),1,archivo);

                validos=strcmp(user, a.usuario);
                ///MostrarMatriz("Pass Encript\n",a.pas);
                DesencripPass(a.pas, contrasena);

                z=strlen(contrasena);
                contrasena[z]='\0';
                ///MostrarArregloPass(contrasena);
                validos1=strcmp(pass,contrasena);
                if(validos==0 && validos1==0)
                {
                    validos=1;
                    validos1=1;
                    j=1;
                }
                else
                {
                    validos1=0;
                    validos=0;

                }

            }
        system("cls");
        if(validos==0 && validos1==0)
        {
            printf("\nUsuario o contraseña incorrecta, queda %d intentos \n\n", h-1);
            h--;
        }
        contador++;

   }

    while (j==0 && contador <3);

    if(validos ==1 && validos1 ==1)
    {

        printf("\nEl Legeo fue correcto\n");
    }
    else
    {
        printf("\nEl Logeo fue erroneo\n");

    }
//    fread(&a, sizeof(Usuario)-sizeof(Usuario),1,archivo);

    fclose(archivo);

return j;
}




