import MiFieldSet from "./MiFieldSet.jsx"
import DatosPersonales from "./DatosPersonales.jsx"
import DatosEscolares from "./DatosEscolares.jsx"
function Formulario (){
    return (
<form action>
    {/*MiFieldSet titulo="Datos Personales" txt1="Nombre" txt2="Password"/>*/}
    <DatosPersonales></DatosPersonales>
    <DatosEscolares></DatosEscolares>
    {/*<MiFieldSet titulo="Datos Generales" txt1="Direccion" txt2="Correo"/>*/}
    <input type="submit" value="Enviar datos"/>
</form>
    )
}

export default Formulario