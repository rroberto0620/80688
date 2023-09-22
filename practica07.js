const formulario = document.getElementById("formulario");
        let componentes = (inf, nom, correo) => {
            return`
            <fieldset id="infPer">
            <legend>${inf}</legend>
            <label for="name">${nom} </label>
            <input type="text" name="name" id="name">
            <label for="name">${correo} </label>
            <input type="email" name="name" id="email">
            </fieldset> 
            `
        }
        let env = (boton) => {
        return `
            <input type="submit"${boton} value="Enviar" id="b"></input>
        `
        }

        formulario.insertAdjacentHTML("afterbegin",env())
        formulario.insertAdjacentHTML("afterbegin", componentes("Informacion Direccion","Direccion:","Ciudad:"))
        formulario.insertAdjacentHTML("afterbegin", componentes("Informacion Personal","Nombre:","Correo electronico:"))
        

