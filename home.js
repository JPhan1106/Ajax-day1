// function generateCategories (){
//     const xhttp = new XMLHttpRequest();
//     xhttp.open("GET", "http://localhost:8080/be4-webservice/allCategories");
//     xhttp.send();

//     xhttp.onload = function() {
//         const categoryArray = JSON.parse(this.responseText);
//         categoryArray.forEach(item => {
//             document.getElementById("categories").innerHTML += item.name + " ";
//         });




//     }
// }


function generateProducts() {
    const xhttp = new XMLHttpRequest();
    xhttp.open("GET", "http://localhost:8080/be4-webservice/allProducts");
    xhttp.send();
  
    xhttp.onload = function() {
        const productArray = JSON.parse(this.responseText);
        productArray.forEach(item => {
        const newDiv = document.createElement("div");
        
        const productName = document.createElement("span");
        productName.className = "product-name";
        productName.innerHTML = item.name;
        newDiv.appendChild(productName);
  
        const updateButton = document.createElement("button");
        updateButton.className = "update-button";
        updateButton.innerHTML = "Update";
  
        updateButton.addEventListener("click", function() {
            showUpdateForm(item.id);
            });

        newDiv.appendChild(updateButton);
        
        const removeButton = document.createElement("button");
        removeButton.className = "remove-button";
        removeButton.innerHTML = "Remove";
  
        removeButton.addEventListener("click", function() {
          removeProduct(item.id);

;
        });

        newDiv.appendChild(removeButton);

  
        document.getElementById("products").appendChild(newDiv);
      });
    };
}

window.onload = function() {
    generateProducts();
};

function showUpdateForm(productId) {
    const updateForm = document.getElementById("updateForm");
    const saveUpdateButton = document.getElementById("saveUpdateButton");
    saveUpdateButton.addEventListener("click", function(){
        saveUpdate(productId);
        console.log(productId);
    });
    const xhttp = new XMLHttpRequest();
    xhttp.open("GET", "http://localhost:8080/be4-webservice/product?id=" + productId);
    xhttp.send();
  
    xhttp.onload = function() {
    const product = JSON.parse(this.responseText);
    populateUpdateForm (product);
    updateForm.style.display = "block";
    saveUpdateButton.style.display = "block";

    };
}

function populateUpdateForm(product) {
    console.log("populateUpdateForm function called with product:", product);
    document.getElementById("product-name").value = product.name;
    document.getElementById("product-author").value = product.author;
    document.getElementById("product-stock").value = product.stock
    document.getElementById("product-price").value = product.price;
}

function saveUpdate (productId) { 
    const productName = document.getElementById("product-name").value;
    const productAuthor = document.getElementById("product-author").value;
    const productStock = document.getElementById("product-stock").value;
    const productPrice = document.getElementById("product-price").value;

    const productData = {
        id: productId,
        name: productName,
        author: productAuthor,
        stock: productStock,
        price: productPrice
    };

    // Convert the productData object to a JSON string
    const jsonData = JSON.stringify(productData);

    const xhttp = new XMLHttpRequest();
    xhttp.open("POST", "http://localhost:8080/be4-webservice/updateProduct");
    xhttp.setRequestHeader("Content-Type", "application/json"); // Set the request header to indicate JSON data
    xhttp.send(jsonData);
}

function removeProduct(productId) {
    const xhttp = new XMLHttpRequest();
    xhttp.open("GET", "http://localhost:8080/be4-webservice/removeProduct?id=" + productId);
    xhttp.send();

    xhttp.onload = function() {
        generateProducts();
    };
}