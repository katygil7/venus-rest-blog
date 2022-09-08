import CreateView from "../createView.js";

export default function PostIndex(props) {
    return `
        <header>
            <h1>Posts Page</h1>
        </header>
        <main>
            <h3>List of posts</h3>
            <div>
                ${props.posts.map(post => `<h3>${post.title}</h3>`).join('')}   
            </div>
            <h3>add a post</h3>
            <form action="">
                <label for="title">Title</label>
                <input id="title" name="title" type="text" placeholder="Enter tittle">
                <label for="content">Content</label><br>
                <textarea name="content" id="content" cols="50" rows="10" placeholder="Enter content"></textarea>
                <button id="addPost" name="addPost">Add post</button>
            </form>
        </main>
    `;
}
export function postSetup(){
    addPostHandler();
}
export function addPostHandler(){
    const addButton = document.querySelector("#addPost")
    console.log("Doing post setup")
    addButton.addEventListener("click", function (event){
        const titleField = document.querySelector("#title");
        const contentField = document.querySelector("#content");
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

        fetch("http://localhost:8080/api/posts", request)
            .then(response => {
                console.log(response.status);
                CreateView("/posts");
            })
    });
}