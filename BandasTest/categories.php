<?php 

require_once("../NicolasEstigarribia/config/constants.php");
require_once("../NicolasEstigarribia/config/autoload.php");

include('header.php'); 

 use daos\Categories as Categories;
$repo = new Categories();
$categories = $repo->getAll();

?>

<body>
    <?php include('nav.php'); ?>

    <div class="uk-container">

        <main class="uk-padding">

            <form action="">
                <div class="uk-margin">
                    <input class="uk-input" type="text" placeholder="Nombre de la categoría">
                </div>

                <button type="submit" class="uk-button uk-button-primary">Agregar Categoría</button>
            </form>

            <hr>

            <table class="uk-table">
                <thead>
                    <tr>
                        <th width="30%">ID</th>
                        <th>Nombre</th>
                        <th width="30%">Estado</th>
                    </tr>
                </thead>
                <tbody>
                <?php foreach($categories as $categorie){?>
                    <tr>
                    <td><?php echo $categorie->getId()?></td>
                    <td><?php echo $categorie->getName()?></td>
                        <td>
                        <?php echo $categorie->getIsActive()?>
                        </td>
                    </tr>
                    <?php } ?>
                </tbody>
            </table>
    </main>
    </div>
</body>

<?php include('footer.php'); ?>