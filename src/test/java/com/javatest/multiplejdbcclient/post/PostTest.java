package com.javatest.multiplejdbcclient.post;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.jdbc.core.simple.JdbcClient;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class PostTest {

    @InjectMocks
    private PostService postService;

    @Mock
    private JdbcClient jdbcClient;

    @Mock
    private JdbcClient.StatementSpec statementSpec;

    @Mock
    private JdbcClient.MappedQuerySpec<Post> querySpec;

    @BeforeEach
    public void setup()  {
        // Inicializa los mocks antes de cada prueba
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetPosts() {
        // Datos de prueba
        Post post1 = new Post("1", "Title 1", "Content 1", LocalDate.now(), 10, "tag1");
        Post post2 = new Post("2", "Title 2", "Content 2", LocalDate.now(), 20, "tag2");
        List<Post> posts = Arrays.asList(post1, post2);

        // Configura el comportamiento esperado del mock jdbcClient
        when(jdbcClient.sql(any(String.class))).thenReturn(statementSpec);
        // Establece que cuando se llama al método sql() del mock jdbcClient con cualquier cadena como argumento, debe devolver el mock statementSpec

        when(statementSpec.query(Post.class)).thenReturn(querySpec);
        // Establece que cuando se llama al método query() del mock statementSpec con la clase Post como argumento, debe devolver el mock querySpec

        when(querySpec.list()).thenReturn(posts);
        // Establece que cuando se llama al método list() del mock querySpec, debe devolver la lista de posts

        // Llama al método a probar: getPosts
        List<Post> result = postService.getPosts();

        // Verifica que los resultados obtenidos sean los esperados
        assertEquals(posts, result);
        // Comprueba que la lista de posts devuelta sea igual a la lista de posts esperada
    }


    @Test
    public void testFindPostById() {
        // Datos de prueba
        Post post = new Post("1", "Title 1", "Content 1", LocalDate.now(), 10, "tag1");
        String id = "1";

        // Configura el comportamiento esperado del mock jdbcClient
        when(jdbcClient.sql(any(String.class))).thenReturn(statementSpec);
        // Establece que cuando se llama al método sql() del mock jdbcClient con cualquier cadena como argumento, debe devolver el mock statementSpec

        when(statementSpec.param("id", id)).thenReturn(statementSpec);
        // Establece que cuando se llama al método param() del mock statementSpec con la clave "id" y el valor id como argumentos, debe devolver el mismo mock statementSpec

        when(statementSpec.query(Post.class)).thenReturn(querySpec);
        // Establece que cuando se llama al método query() del mock statementSpec con la clase Post como argumento, debe devolver el mock querySpec

        when(querySpec.optional()).thenReturn(Optional.of(post));
        // Establece que cuando se llama al método optional() del mock querySpec, debe devolver un Optional que contenga el objeto post

        // Llama al método a probar: findPostById
        Optional<Post> result = postService.findPostById(id);

        // Verifica que el resultado obtenido sea el esperado
        assertEquals(Optional.of(post), result);
        // Comprueba que el Optional devuelto contenga el objeto post esperado
    }


    @Test
    public void testCreatePost() {
        // Datos de prueba
        Post post = new Post("3", "Title 3", "Content 3", LocalDate.now(), 30, "tag3");

        // Configura el comportamiento esperado del mock jdbcClient
        when(jdbcClient.sql(any(String.class))).thenReturn(statementSpec);
        // Establece que cuando se llama al método sql() del mock jdbcClient con cualquier cadena como argumento, debe devolver el mock statementSpec

        when(statementSpec.params(post)).thenReturn(statementSpec);
        // Establece que cuando se llama al método params() del mock statementSpec con el objeto post como argumento, debe devolver el mismo mock statementSpec

        when(statementSpec.update()).thenReturn(1);
        // Establece que cuando se llama al método update() del mock statementSpec, debe devolver 1, indicando que la inserción se realizó con éxito

        // Llama al método a probar: createPost
        postService.createPost(post);

        // Verifica que se ejecutó correctamente la consulta SQL esperada y que se configuraron correctamente los parámetros y se actualizó la base de datos
        verify(jdbcClient).sql("INSERT INTO post (id, title, content, date, time_to_read, tags) " +
                               "VALUES (:id, :title, :content, :date, :timeToRead, :tags)");
        // Se verifica que se haya llamado al método sql() del mock jdbcClient con la consulta SQL esperada para insertar un nuevo post

        verify(statementSpec).params(post);
        // Se verifica que se haya llamado al método params() del mock statementSpec con el objeto post, lo que indica que se configuraron correctamente los parámetros en la consulta SQL

        verify(statementSpec).update();
        // Se verifica que se haya llamado al método update() del mock statementSpec, lo que indica que se ejecutó la consulta SQL de inserción con éxito en la base de datos
    }


    @Test
    public void testUpdatePost() {
        // Datos de prueba
        Post post = new Post("1", "Updated Title", "Updated Content", LocalDate.now(), 15, "updatedTag");

        // Configura el comportamiento esperado del mock jdbcClient
        when(jdbcClient.sql(any(String.class))).thenReturn(statementSpec);
        // Establece que cuando se llama al método sql() del mock jdbcClient con cualquier cadena como argumento, debe devolver el mock statementSpec

        when(statementSpec.params(post)).thenReturn(statementSpec);
        // Establece que cuando se llama al método params() del mock statementSpec con el objeto post como argumento, debe devolver el mismo mock statementSpec

        when(statementSpec.update()).thenReturn(1);
        // Establece que cuando se llama al método update() del mock statementSpec, debe devolver 1, indicando que la actualización se realizó con éxito

        // Llama al método a probar: updatePost
        postService.updatePost(post);

        // Verifica que se ejecutó correctamente la consulta SQL esperada y que se configuraron correctamente los parámetros y se actualizó la base de datos
        verify(jdbcClient).sql("UPDATE post SET title = :title, " +
                               "content = :content," +
                               " date = :date, " +
                               "time_to_read = :timeToRead," +
                               " tags = :tags " +
                               "WHERE id = :id");
        // Se verifica que se haya llamado al método sql() del mock jdbcClient con la consulta SQL esperada para actualizar un post

        verify(statementSpec).params(post);
        // Se verifica que se haya llamado al método params() del mock statementSpec con el objeto post, lo que indica que se configuraron correctamente los parámetros en la consulta SQL

        verify(statementSpec).update();
        // Se verifica que se haya llamado al método update() del mock statementSpec, lo que indica que se ejecutó la consulta SQL de actualización con éxito en la base de datos
    }


    @Test
    public void testDeletePost() {
        // Datos de prueba
        String id = "1";

        // Configura el comportamiento esperado del mock jdbcClient
        when(jdbcClient.sql(any(String.class))).thenReturn(statementSpec);
        // Establece que cuando se llama al método sql() del mock jdbcClient con cualquier cadena como argumento, debe devolver el mock statementSpec

        when(statementSpec.param("id", id)).thenReturn(statementSpec);
        // Establece que cuando se llama al método param() del mock statementSpec con los argumentos "id" y el valor de la variable id, debe devolver el mismo mock statementSpec

        when(statementSpec.update()).thenReturn(1);
        // Establece que cuando se llama al método update() del mock statementSpec, debe devolver 1, indicando que la actualización se realizó con éxito

        // Llama al método a probar: deletePost
        int result = postService.deletePost(id);

        // Verifica que se ejecutó correctamente la consulta SQL esperada y que se configuraron correctamente los parámetros y se actualizó la base de datos
        assertEquals(1, result);
        // Se verifica que el resultado de llamar al método deletePost() sea 1, lo que indica que se realizó correctamente la eliminación del post con el id proporcionado

        verify(jdbcClient).sql("DELETE FROM post WHERE id = :id");
        // Se verifica que se haya llamado al método sql() del mock jdbcClient con la consulta SQL esperada para eliminar un post con un id específico

        verify(statementSpec).param("id", id);
        // Se verifica que se haya llamado al método param() del mock statementSpec con los argumentos "id" y el valor de la variable id, lo que indica que se estableció correctamente el parámetro en la consulta SQL

        verify(statementSpec).update();
        // Se verifica que se haya llamado al método update() del mock statementSpec, lo que indica que se ejecutó la consulta SQL de eliminación con éxito en la base de datos
    }

}
