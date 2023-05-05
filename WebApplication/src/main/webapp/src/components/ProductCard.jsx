import React from "react";
import PropTypes from "prop-types";
import {Link} from "react-router-dom";


const ProductCard = (props) => {
    return (
        <div className="product-card">
            <Link to={`/book/${props.id}`}>
                <div className="product-card__image">
                    <img src={props.image01} alt={props.name}/>
                </div>
                <h3 className="product-card__name">{props.name}</h3>
                <p className="product-card__author">{props.author}</p>
            </Link>
            <div className="product-card__btn">

            </div>
        </div>
    );
};


export default ProductCard;
