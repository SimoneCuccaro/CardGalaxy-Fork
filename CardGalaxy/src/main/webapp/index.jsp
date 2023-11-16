<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="./CSS/index-style.css">
    <link rel="stylesheet" href="./CSS/support-button.css">
    <title>Homepage</title>
</head>
<body>
<header>
    <div class="user-svg">
        <%@include file="icons/user-solid.svg"%>
    </div>
    <div class="cart-svg">
        <%@include file="icons/cart.svg"%>
        <span class="badge">0</span>
    </div>
    <img id="logo" src="photos/cardgalaxylogo.png" alt="website logo">
</header>
<nav>
    <ul>
        <li><a href="#">Home</a></li>
        <li><a href="#">About Us</a></li>
        <li><a href="#">Shop Now</a></li>
    </ul>
</nav>
<main>
    <div class="content">
        <h1 class="welcome">Welcome to the Galaxy of Gift Cards</h1>
        <img class="welcome-animation" src="photos/card_anim_welcome.gif" alt="card animation">
        <p class="para_index">
            All the Gift Cards that you need in ONE PLACE.
        </p>
        <img class="promo-cards" src="photos/cards_promo.png" alt="promo cards">
        <p class="para_index">
             What are you waiting for? <a class="index-link" href="#">SHOP NOW</a>
        </p>
    </div>
</main>
<footer>
    &copy; 2023 CardGalaxy Inc. All rights reserved.
    <p>
        Need Help?
    </p>
    <button type="submit" class="button"> CLICK HERE </button>
</footer>
</body>
</html>
