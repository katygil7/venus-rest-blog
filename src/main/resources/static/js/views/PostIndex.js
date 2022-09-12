import CreateView from "../createView.js";

export default function PostIndex(props) {
    const postsHTML = generatePostsHTML(props.posts)
    // posts = props.posts;
    return `
        <header>
            <h1>Posts Page</h1>
        </header>
        <main>
            <h3>List of posts</h3>      
            <div>
                ${postsHTML}   
            </div>
            <br>
            <h2>add a post</h2>
            <form action="">
                <label for="title">Title</label>
                <input id="title" name="title" type="text" placeholder="Enter tittle"><br>
                <label for="content">Content</label><br>
                <textarea name="content" id="content" cols="50" rows="10" placeholder="Enter content"></textarea>
                <button id="savePost" name="savePost">Save</button>
            </form>
        </main>
    `;
}
function generatePostsHTML(posts) {
    let postsHTML = `
        <table class="table">
        <thead>
        <tr>
            <th scope="col">Title</th>
            <th scope="col">Content</th>
        </tr>
        </thead>
        <tbody>
    `;
    for (let i = 0; i < posts.length; i++) {
        const post = posts[i];
        postsHTML += `<tr>
            <td>${post.title}</td>
            <td>${post.content}</td>
            <td><button data-id=${post.id} class="editPost">Edit</button></td>
            <td><button data-id=${post.id} class="deletePost">Delete</button></td>
            </tr>`;
    }
    postsHTML += `</tbody></table>`;
    return postsHTML;
}

export function setupSaveHandler() {
    const saveButton = document.querySelector("#savePost")
    console.log("Doing post setup")
    saveButton.addEventListener("click", function (event) {
        const titleField = document.querySelector("#title");
        const contentField = document.querySelector("#content");
        //make the new updated post
        let newPost = {
            title: titleField.value,
            content: contentField.value,
        }
        console.log(newPost);

        let request = {
            method: "POST",
            headers: {"Content-Type": "application/json"},
            body: JSON.stringify(newPost)
        }
        let url = "http://localhost:8080/api/posts"
        fetch(url, request)
            .then(function (response) {
                console.log(response.status);
                CreateView("/posts");
            })
    });
}
    function editPostHandlers() {
        const editButtons = document.querySelector(".editPost");
        const tittleField = document.querySelector("#title");
        const contentField = document.querySelector("#content");
        for (let i = 0; i < editButtons.length; i++) {
            editButtons[i].addEventListener("click", function (event) {
                if ((tittleField.value === "") || (contentField.value === "")) {
                    console.log("blank");
                } else {
                    let editPost = {
                        title: tittleField.value,
                        content: contentField.value
                    }
                    let request = {
                        method: "PUT",
                        headers: {"content-Type": "application/json"},
                        body: JSON.stringify(editPost)
                    }
                    let url = `http://localhost:8080/api/posts/${editButtons[i].getAttribute("data-id")}`;
                    fetch(url, request)
                        .then(response => response.json());
                    location.reload();
                }
            });
        }
    }

    function deletePostHandlers() {
        const deleteButtons = document.querySelectorAll(".deletePost");
        for (let i = 0; i < deleteButtons.length; i++) {
            deleteButtons[i].addEventListener("click", function (event) {
                console.log("delete");
                const postId = this.getAttribute("data-id")
                deletePost(postId);
            })
        }
    }
    function deletePost (){
        let request = {
            method: "DELETE",
            headers: {"Content-Type": "application/json"},
        }
        let url = "http://localhost:8080/api/posts/" + postId;
        fetch(url, request)
            .then(response => response.json());
        location.reload();
    }


    export function PostSetup() {
        setupSaveHandler();
        editPostHandlers();
        deletePostHandlers();
    }
