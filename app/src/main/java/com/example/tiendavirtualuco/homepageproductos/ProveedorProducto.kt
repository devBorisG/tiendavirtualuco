package com.example.tiendavirtualuco.homepageproductos

import com.example.tiendavirtualuco.homepageproductos.model.ModeloProducto

class ProveedorProducto {
    companion object {
        val listaProductos = listOf<ModeloProducto>(
            ModeloProducto(
                id = 1,
                nombreProducto = "Manzanas Orgánicas (1 kg)",
                cantidad = 10,
                precioProducto = 3.99,
                descripcion = "Manzanas cultivadas de forma orgánica, sin pesticidas ni químicos.",
                imagenProducto = "https://th.bing.com/th/id/OIP.asF2VlsyUKGHxl6TolL5xQHaEK?rs=1&pid=ImgDetMain",
                es_oferta = false,
                precio_oferta = 0.0,
                precio_envio = 12.0,
                porcentaje_descuento = "0%"
            ),
            ModeloProducto(
                id = 2,
                nombreProducto = "Pan Integral Multigrano",
                cantidad = 20,
                precioProducto = 2.49,
                descripcion = "Pan integral hecho con una mezcla de granos enteros para un sabor y textura ricos en nutrientes.",
                imagenProducto = "https://th.bing.com/th/id/R.bd0c586b32287e3c5f7fd0c6abcb834c?rik=YrB901Sqyt%2fzoA&riu=http%3a%2f%2fwww.delicias.tv%2fblog%2fwp-content%2fuploads%2f2017%2f02%2fportada-44.jpg&ehk=9XrYIBxSRUDgrlosFK%2f219vf%2bvdfy%2fBPscOkuZ3A1Os%3d&risl=&pid=ImgRaw&r=0",
                es_oferta = false,
                precio_oferta = 0.0,
                precio_envio = 12.0,
                porcentaje_descuento = "0%"
            ),
            ModeloProducto(
                id = 3,
                nombreProducto = "Leche Deslactosada (1 L)",
                cantidad = 40,
                precioProducto = 1.49,
                descripcion = "Leche deslactosada ideal para personas intolerantes a la lactosa.",
                imagenProducto = "https://th.bing.com/th/id/OIP.MvSgtIz5ZF6aCy5bkEywbQHaE8?w=292&h=194&c=7&r=0&o=5&dpr=1.3&pid=1.7",
                es_oferta = false,
                precio_oferta = 0.0,
                precio_envio = 12.0,
                porcentaje_descuento = "0%"
            ),
            ModeloProducto(
                id = 4,
                nombreProducto = "Café Molido Colombiano (500 g)",
                cantidad = 20,
                precioProducto = 7.99,
                descripcion = "Café molido de alta calidad originario de Colombia.",
                imagenProducto = "https://th.bing.com/th/id/OIP.5PU_7VujE-cBdzenhJ2RRQHaE8?w=283&h=189&c=7&r=0&o=5&dpr=1.3&pid=1.7",
                es_oferta = false,
                precio_oferta = 0.0,
                precio_envio = 12.0,
                porcentaje_descuento = "0%"
            ),
            ModeloProducto(
                id = 5,
                nombreProducto = "Queso Cheddar Maduro (200 g)",
                cantidad = 30,
                precioProducto = 4.99,
                descripcion = "Queso cheddar maduro con un sabor intenso y textura cremosa.",
                imagenProducto = "https://th.bing.com/th/id/OIP.B_5wc352Gp2JzZbox0WtzgHaDh?rs=1&pid=ImgDetMain",
                es_oferta = false,
                precio_oferta = 0.0,
                precio_envio = 12.0,
                porcentaje_descuento = "0%"
            ),
            ModeloProducto(
                id = 6,
                nombreProducto = "Huevos Orgánicos (Docena)",
                cantidad = 0,
                precioProducto = 3.49,
                descripcion = "Huevos orgánicos provenientes de gallinas criadas en libertad.",
                imagenProducto = "https://th.bing.com/th/id/OIP.MDFQMkXd4rgUOVL6DrlDvAHaE8?w=221&h=180&c=7&r=0&o=5&dpr=1.3&pid=1.7",
                es_oferta = false,
                precio_oferta = 0.0,
                precio_envio = 12.0,
                porcentaje_descuento = "0%"
            ),
            ModeloProducto(
                id = 7,
                nombreProducto = "Yogur Natural (1 L)",
                cantidad = 10,
                precioProducto = 2.99,
                descripcion = "Yogur natural sin azúcar añadido, perfecto para una dieta saludable.",
                imagenProducto = "https://th.bing.com/th/id/OIP.-gkZA3kGfIUNEzwZFTRwvgHaE8?w=266&h=180&c=7&r=0&o=5&dpr=1.3&pid=1.7",
                es_oferta = false,
                precio_oferta = 0.0,
                precio_envio = 12.0,
                porcentaje_descuento = "0%"
            ),
            ModeloProducto(
                id = 8,
                nombreProducto = "Avena en Hojuelas (500 g)",
                cantidad = 40,
                precioProducto = 2.29,
                descripcion = "Avena en hojuelas ideal para desayunos nutritivos.",
                imagenProducto = "https://th.bing.com/th/id/OIP.Yqz72YiDh4uChDiybOa1rAHaE8?w=302&h=201&c=7&r=0&o=5&dpr=1.3&pid=1.7",
                es_oferta = false,
                precio_oferta = 0.0,
                precio_envio = 12.0,
                porcentaje_descuento = "0%"
            ),
            ModeloProducto(
                id = 9,
                nombreProducto = "Miel de Abeja Pura (250 g)",
                cantidad = 50,
                precioProducto = 5.99,
                descripcion = "Miel de abeja pura y natural sin aditivos.",
                imagenProducto = "https://th.bing.com/th/id/OIP.4L5q70DXCsiUZJdxD2XkhQHaHa?w=197&h=197&c=7&r=0&o=5&dpr=1.3&pid=1.7",
                es_oferta = false,
                precio_oferta = 0.0,
                precio_envio = 12.0,
                porcentaje_descuento = "0%"
            ),
            ModeloProducto(
                id = 10,
                nombreProducto = "Bananas Ecológicas (1 kg)",
                cantidad = 60,
                precioProducto = 1.99,
                descripcion = "Bananas cultivadas de forma ecológica, sin pesticidas.",
                imagenProducto = "https://th.bing.com/th/id/OIP.DwuEOyPzs301vVKJBWqXsAHaFh?w=248&h=186&c=7&r=0&o=5&dpr=1.3&pid=1.7",
                es_oferta = false,
                precio_oferta = 0.0,
                precio_envio = 12.0,
                porcentaje_descuento = "0%"
            ),
            ModeloProducto(
                id = 11,
                nombreProducto = "Aceite de Oliva Extra Virgen (500 ml)",
                cantidad = 90,
                precioProducto = 8.99,
                descripcion = "Aceite de oliva extra virgen de primera prensada en frío.",
                imagenProducto = "https://th.bing.com/th?id=OIF.Npdg%2bErNggS31VC3%2bQYRTQ&w=179&h=180&c=7&r=0&o=5&dpr=1.3&pid=1.7",
                es_oferta = false,
                precio_oferta = 0.0,
                precio_envio = 12.0,
                porcentaje_descuento = "0%"
            ),
            ModeloProducto(
                id = 12,
                nombreProducto = "Arroz Integral (1 kg)",
                cantidad = 10,
                precioProducto = 2.69,
                descripcion = "Arroz integral rico en fibra y nutrientes.",
                imagenProducto = "https://th.bing.com/th/id/OIP.G4_x9ikXjAM4pEUR9Ei3zwHaGq?w=208&h=187&c=7&r=0&o=5&dpr=1.3&pid=1.7",
                es_oferta = false,
                precio_oferta = 0.0,
                precio_envio = 12.0,
                porcentaje_descuento = "0%"
            ),
            ModeloProducto(
                id = 13,
                nombreProducto = "Tomates Cherry (500 g)",
                cantidad = 0,
                precioProducto = 2.49,
                descripcion = "Tomates cherry frescos y jugosos, perfectos para ensaladas.",
                imagenProducto = "https://th.bing.com/th/id/OIP.kc6LkDmwxTZl8o0Un1opdAHaE8?w=262&h=180&c=7&r=0&o=5&dpr=1.3&pid=1.7",
                es_oferta = false,
                precio_oferta = 0.0,
                precio_envio = 12.0,
                porcentaje_descuento = "0%"
            ),
            ModeloProducto(
                id = 14,
                nombreProducto = "Salmón Ahumado (200 g)",
                cantidad = 30,
                precioProducto = 9.99,
                descripcion = "Salmón ahumado de alta calidad, rico en omega-3.",
                imagenProducto = "https://th.bing.com/th/id/OIP.BN6lXTvlD2y4Dxrb_ILDcQHaE5?w=262&h=180&c=7&r=0&o=5&dpr=1.3&pid=1.7",
                es_oferta = false,
                precio_oferta = 0.0,
                precio_envio = 12.0,
                porcentaje_descuento = "0%"
            ),
            ModeloProducto(
                id = 15,
                nombreProducto = "Pasta Integral Fusilli (500 g)",
                cantidad = 0,
                precioProducto = 1.99,
                descripcion = "Pasta integral tipo fusilli, alta en fibra y nutrientes.",
                imagenProducto = "https://th.bing.com/th/id/OIP.ooh10HPe1-LgUPf0qu7j1gHaE9?rs=1&pid=ImgDetMain",
                es_oferta = false,
                precio_oferta = 0.0,
                precio_envio = 12.0,
                porcentaje_descuento = "0%"
            )
        )
    }
}
