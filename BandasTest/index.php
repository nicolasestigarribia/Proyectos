<?php 

require_once("../NicolasEstigarribia/config/constants.php");
require_once("../NicolasEstigarribia/config/autoload.php");

include('header.php'); 


    use daos\Bands as Bands;

    $repo = new Bands();
    $bands = $repo->getAll();


?>

<body>
    <?php include('nav.php'); ?>

    <div class="uk-container">

        <main class="uk-padding">

            <form action="<?php echo "process/update.php" ?>" method="POST">
                <div class="uk-margin">
                    <input class="uk-input" type="text" placeholder="Nombre de la banda">
                </div>
                <div class="uk-margin">
                    <textarea class="uk-textarea" rows="5" placeholder="Biografía de la banda"></textarea>
                </div>
                <button type="submit" class="uk-button uk-button-primary">Agregar banda</button>
            </form>

            <hr>

            <table class="uk-table">
                <thead>
                <?php foreach($bands as $banda) :?>
                    <tr>
                        <th>ID</th>
                        <th width="30%">Banda</th>
                        <th>Bio</th>
                        <th width="30%">Integrantes</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td><?php echo $banda->getId();?></td>
                        <form action="<?php echo "process/remove.php" ?>" method="POST">
                        <td><button type="submit" name="remove" class="uk-button uk-button-danger" value="<?php echo $banda->getId() ?>">Eliminar Banda<?php echo " ".$banda->getId() ?></button></td>
                        </form>
                        
                        <td>
                        <form action="<?php echo "process/update.php" ?>" method="POST">
                            <strong><button type="submit" name="edit" value="<?php echo $banda->getName() ?>"><?php echo " ".$banda->getName() ?></button></td></strong>
                        </td>
                        <td><?php echo $banda->getBio(); ?></td>
                        <td>
                        <?php foreach ($banda->getMembers() as $value) :?>
                        
                            <ul>
                                <li><?php echo $value->getName()?></li>

                            </ul>
                        <?php endforeach; ?>
                        </td>
                    </tr>
                </tbody>
                <?php endforeach; ?>
            </table>
    </main>
    </div>
</body>

<?php include('footer.php'); ?>