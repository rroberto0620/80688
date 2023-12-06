import { Button, Box, TextField } from "@mui/material"
import axios from "axios"
import { useState } from "react"

function Formulario(props) {
    const [Cargando, setCargando] = useState (false)
    const [datosFormulario, setDatosFormulario] = useState( {nombre:'', correo:'',telefono:''} )

    const hacerPeticion = async () => {
        try {
            const response = await axios.get('http://localhost:4567/consultar',{params:datosFormulario})
            console.log("hacerPeticion", response)
            return response.data
        } catch (error) {
            throw error
        }
    }

    const agregarDato = async () => {
        try{
            const responde = await axios.put('http://localhost:4567/agregar',{params:datosFormulario})
            console.log("agregarDato",response)
            return response.data
        }catch (error){
            throw error
        }
    }

    const quitarDato = async () => {
        try{
            const responde = await axios.put('http://localhost:4567/eliminar',{params:datosFormulario})
            console.log("quitarDato",response)
            return response.data
        }catch (error){
            throw error
        }
    }

    const modificarDato = async () => {
        try{
            const responde = await axios.put('http://localhost:4567/modificar',{params:datosFormulario})
            console.log("modificarDato",response)
            return response.data
        }catch (error){
            throw error
        }
    }

    const cambiosFormulario = (evento) => {
        //console.log(evento.target)
        const {name, value} = evento.target
        setDatosFormulario( { ...datosFormulario, [name] : value })
    }

    const procesarFormulario = async (evento) => {
        evento.preventDefault()
        console.log("datos recuperados el form:", datosFormulario)
        setCargando(true)
        try {
            const response = await hacerPeticion()
            console.log("procesarFormulario", response)
            setCargando(false)
            if (datosFormulario.nombre === response.tipousuario) {
                console.log("ok el usuario es correcto")
            } else {
                console.log("error el usuario es incorrecto")
            }
        } catch (error) {
            console.log("error", error)
            setCargando(false)
        }
    }

    return (
        <>
            <h1>Administrar Datos - Proyecto</h1>
            <form onSubmit={ procesarFormulario }>
                <Box m={5}>
                    <TextField label="Nombre:" variant="outlined" fullWidth onChange={cambiosFormulario} name="nombre" value={datosFormulario.nombre}></TextField>
                </Box>
                <Box m={5}>
                    <TextField label="Correo:" variant="outlined" fullWidth onChange={cambiosFormulario} name="correo" value={datosFormulario.correo}></TextField>
                </Box>
                <Box m={5}>
                    <TextField label="Telefono:" variant="outlined" fullWidth onChange={cambiosFormulario} name="telefono" value={datosFormulario.telefono}></TextField>
                </Box>
                <Box m={5}>
                    <Button variant="contained" type="submit" color="inherit" fullWidth disabled={Cargando}>Agregar</Button>
                </Box>
                <Box m={5}>
                    <Button variant="contained" type="submit" color="error" fullWidth disabled={Cargando}>Eliminar</Button>
                </Box>
                <Box m={5}>
                    <Button variant="contained" type="submit" color="success" fullWidth disabled={Cargando}>Actualizar</Button>
                </Box>
                <Box m={5}>
                    <Button variant="contained" type="submit" color="info" fullWidth disabled={Cargando}>Consulta</Button>
                </Box>
            </form>
        </>
    )
}

export default Formulario