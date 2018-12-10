exports.filter = function(products, available, unavailable,
    price0to25, price25to50, price50to100, thumb1, thumb2, genre) {
  return new Promise(function(resolve, reject) {
    // TODO: implement own project filter functionality here
    // reject(new Error('TODO')); // remove this when you start
    resolve(products.filter(function(product) {
      //Setting up fail conditions
      if (available && !unavailable && product['stock'] <= 0){
          return false;
      }
      if (unavailable && !available && product['stock'] > 0){
          return false;
      }
      // if 0 to 25 checked
      if (price0to25 && !price25to50 && !price50to100 && (product["price"] < 0 || product["price"] > 25)){
          return false;
      }
      // if 0 to 25, 25 to 50 checked
      if (price25to50 && price0to25 && !price50to100 && (product["price"] < 0 || product["price"] > 50)){
          return false;
      }
      // if 0 to 25, 50 to 100 checked
      if (price0to25 && price50to100 && !price25to50 && ( (product["price"] < 50 && product["price"] > 25) || product["price"] < 0 || product["price"] > 100)){
          return false;
      }
      // if 25 to 50 checked
      if (price25to50 && !price0to25 && !price50to100 && (product["price"] < 25 || product["price"] > 50)){
          return false;
      }
      // if 25 to 50 checked, 50 to 100 checked
      if (price25to50 && price50to100 && !price0to25 && (product["price"] < 50 || product["price"] > 100)){
          return false;
      }
      // if 50 to 100 checked
      if (price50to100 && !price0to25 && !price25to50 && (product["price"] < 50 || product["price"] > 100)){
          return false;
      }
      if (thumb1 && !thumb2 && product["rating"] < 1 ){
          return false;
      }
      if (!thumb1 && thumb2 && product["rating"] < 2 ){
          return false;
      }
      if (thumb1 && thumb2 && product["rating"] < 1 ){
          return false;
      }
      // console.log(product)
      return true;
  }))


  });
};
