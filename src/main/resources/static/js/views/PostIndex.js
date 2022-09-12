import CreateView from "../createView.js";
let posts;
export default function PostIndex(props) {
    const postsHTML = generatePostsHTML(props.posts)
    posts = props.posts;
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
export function PostSetup() {
    setupSaveHandler();
    setupEditHandlers();
    setupDeleteHandlers() ;
}
function setupEditHandlers() {
    const editButtons = document.querySelectorAll(".editPost");
    for (let i = 0; i < editButtons.length; i++) {
        editButtons[i].addEventListener("click", function (event) {
            const postId = parseInt(this.getAttribute("data-id"));
            loadPostIntoForm(postId);
        });
    }
}
function loadPostIntoForm(postId){
    const post = fetchPostById(postId);
    if (!post){
        console.log("did not find post for id" + postId);
        return;
    }
    const titleField = document.querySelector("#title");
    const contentField = document.querySelector("#content");
    titleField.value = post.title;
    contentField.value = post.content;
    const saveButton = document.querySelector("#savePost");
    saveButton.setAttribute("data-id",postId);
}
function fetchPostById(postId){
    for (let i = 0; i < posts.length; i++) {
        if(posts[i].id === postId) {
            return posts[i];
        }
    }
    return false;
}

function setupDeleteHandlers() {
    // target all delete buttons
    const deleteButtons = document.querySelectorAll(".deletePost");
    // add click handler to all delete buttons
    for (let i = 0; i < deleteButtons.length; i++) {
        deleteButtons[i].addEventListener("click", function(event) {

            // get the post id of the delete button
            const postId = this.getAttribute("data-id");

            deletePost(postId);
        });
    }
}
function deletePost(postId) {
    const request = {
        method: "DELETE",
        headers: {"Content-Type": "application/json"},
    }
    const url = "http://localhost:8080/api/posts/" + postId;
    fetch(url, request)
        .then(function(response) {
            CreateView("/posts");
        })
}

export function setupSaveHandler() {
    const saveButton = document.querySelector("#savePost")
    console.log("Doing post setup")
    saveButton.addEventListener("click", function (event) {
        const postId = parseInt(this.getAttribute("data-id"));
        const titleField = document.querySelector("#title");
        const contentField = document.querySelector("#content");
        //make the new updated post
        const post = {
            title: titleField.value,
            content: contentField.value,
        }
        console.log(post);

        let request = {
            method: "POST",
            headers: {"Content-Type": "application/json"},
            body: JSON.stringify(post)
        }
        let url = "http://localhost:8080/api/posts"
        if (postId > 0){
            request.method = "PUT";
            url += `/${postId}`;
        }
        fetch(url, request)
            .then(function (response) {
                console.log(response.status);
                CreateView("/posts");
            })
    });
}