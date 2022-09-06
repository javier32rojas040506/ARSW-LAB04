## Escuela Colombiana de Ingeniería

### Francisco Javier Rojas M. y Juan Camilo Rojas C. 

## Arquitecturas de Software

# Componentes y conectores - Parte I.

El ejercicio se debe traer terminado para el siguiente laboratorio (Parte II).

#### Middleware- gestión de planos.


## Antes de hacer este ejercicio, realice [el ejercicio introductorio al manejo de Spring y la configuración basada en anotaciones](https://github.com/ARSW-ECI/Spring_LightweightCont_Annotation-DI_Example).

En este ejercicio se va a construír un modelo de clases para la capa lógica de una aplicación que permita gestionar planos arquitectónicos de una prestigiosa compañia de diseño. 

![](img/ClassDiagram1.png)

1. Configure la aplicación para que funcione bajo un esquema de inyección de dependencias, tal como se muestra en el diagrama anterior.


	Lo anterior requiere:

	* Agregar las dependencias de Spring.
	* Agregar la configuración de Spring.
	* Configurar la aplicación -mediante anotaciones- para que el esquema de persistencia sea inyectado al momento de ser creado el bean 'BlueprintServices'.

Configuración de Spring (Las dependencias ya vienen en el pom):
	
![img.png](https://media.discordapp.net/attachments/584593411567517710/1016833841278750760/unknown.png)

Notación @Service en la implementación de BluePrintPersistence que deseamos usar:

![img2.png](https://cdn.discordapp.com/attachments/584593411567517710/1016835271519637594/unknown.png)

2. Complete los operaciones getBluePrint() y getBlueprintsByAuthor(). Implemente todo lo requerido de las capas inferiores (por ahora, el esquema de persistencia disponible 'InMemoryBlueprintPersistence') agregando las pruebas correspondientes en 'InMemoryPersistenceTest'.

![img3.png](https://media.discordapp.net/attachments/584593411567517710/1016841457547624498/unknown.png?width=661&height=683)

![img4.png](https://media.discordapp.net/attachments/584593411567517710/1016842487677071420/unknown.png)

3. Haga un programa en el que cree (mediante Spring) una instancia de BlueprintServices, y rectifique la funcionalidad del mismo: registrar planos, consultar planos, registrar planos específicos, etc.

![img5.png](https://media.discordapp.net/attachments/584593411567517710/1016843822178770987/unknown.png?width=1141&height=683)

4. Se quiere que las operaciones de consulta de planos realicen un proceso de filtrado, antes de retornar los planos consultados. Dichos filtros lo que buscan es reducir el tamaño de los planos, removiendo datos redundantes o simplemente submuestrando, antes de retornarlos. Ajuste la aplicación (agregando las abstracciones e implementaciones que considere) para que a la clase BlueprintServices se le inyecte uno de dos posibles 'filtros' (o eventuales futuros filtros). No se contempla el uso de más de uno a la vez:
	* (A) Filtrado de redundancias: suprime del plano los puntos consecutivos que sean repetidos.
	* (B) Filtrado de submuestreo: suprime 1 de cada 2 puntos del plano, de manera intercalada.

	Para esto al igual que hacíamos con la implementación del servicio lo hacemos para la implementación de los filtros:
	
	![img6.png](https://media.discordapp.net/attachments/584593411567517710/1016844596308873308/unknown.png)

	Y lo utilizamos en las dos funciones de búsqueda:

	![img7.png](https://media.discordapp.net/attachments/584593411567517710/1016844704572248135/unknown.png)
	![img8.png](https://media.discordapp.net/attachments/584593411567517710/1016844836034334840/unknown.png)

	Los creamos y a uno de estos le damos la anotación para que sea inyectado:

   ![img9.png](https://media.discordapp.net/attachments/584593411567517710/1016845614648139817/unknown.png)
   ![img10.png](https://cdn.discordapp.com/attachments/584593411567517710/1016845683178881126/unknown.png)
   ![img11.png](https://cdn.discordapp.com/attachments/584593411567517710/1016845773087973407/unknown.png)

5. Agrege las pruebas correspondientes a cada uno de estos filtros, y pruebe su funcionamiento en el programa de prueba, comprobando que sólo cambiando la posición de las anotaciones -sin cambiar nada más-, el programa retorne los planos filtrados de la manera (A) o de la manera (B). 

   Resultado de los test:
   
   ![img12.png](https://media.discordapp.net/attachments/584593411567517710/1016846249078575245/unknown.png)	
   ![img13.png](https://cdn.discordapp.com/attachments/584593411567517710/1016846291688505414/unknown.png)