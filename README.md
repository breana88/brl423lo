# CSE241 final project

Name: Breana Lo  

email: brl423@lehigh.edu  

repo: https://github.com/breana88/brl423lo.git  


## Project description

The goal of this project is to provide a realistic experience in the conceptual design, logical design,
implementation, operation, and maintenance of a relational database and associated applications.  


## Enterprise desciption

The enterprise is Alset Eccentric Vehicles, a next-generation vehicle manufacturer.1 Alset currently
manufactures 4 vehicles, though more are planned: \\

• Model M, the moon vehicle, used to ride on the surface of the moon. \\
• Model U, the undersea vehicle, used as a personal submarine.  \\
• Model S, the spacecraft, used for space travel. This vehicle is capable of landing on most planets and\\
moons, and, after refueling, can re-launch into space.  \\
• Model K, the “Kart”, a small electric vehicle for use on the surface of the earth.\\
  \\ \\
You will be modeling the sales and servicing aspects of Alset’s business. As a new-era vehicle vendor,
Alset makes its sales online and not at dealerships. Customers configure their vehicles online and arrange
payment online. Vehicles are then delivered to a Alset service center for pickup. Those service centers also handle vehicle repair. Not all service centers handle all models (for example, a moon-based service center
would not handle the Model U, while a service center with an oceanfront location on the earth could handle
both the Model K and the Model U).  \\ \\

• customers: Each customer has a name, email address, and physical address. Note that physical
addresses need to be able to include non-US and non-earth addresses given the intergalactic nature
of this business. Customers must have a credit or debit card (since all sales are online ). Alset
management is considering accepting cryptocurrencies, but currently Alset accepts only credit and
debit cards. Alset lists prices in US dollars only and does not accept payment in any fiat currency
other than the US dollar.  \\

• service locations: Alset service locations each handle some subset of the set of Alset vehicle models.
For those models, the service location provides the following services: \\
    – Repair: Alset repairs only its own vehicles. (Sorry, you can’t bring your Tesla here.). Each
    location repairs only those models that it handles.\\
    – Recalls.\\
    – New vehicle delivery: new vehicles are shipped to a service location for customer pickup.\\
    – Showroom: a limited inventory of vehicles for display purposes and for test drives. These vehicles
    are not offered for sale. When a vehicle goes off of display/test-drive status, it goes into the
    used-vehicle fleet.\\
    – Used vehicles: a collection of used Alset vehicles available for sale. Alset does not sell used vehicles
    made by other manufacturers.\\\\

• vehicles: Each vehicle type (the current 4 as well as future vehicles), are offered with a variety of
options. Alset keeps track of each vehicle it produces and the options that vehicle contains. Those
data allow Alset to identify the exact set of vehicles affected by a recall (for example, all 2017 and
2018 Model M vehicles with the crater-descent option). Alset also tracks sales (new and used) and
service visits. Using in-vehicle communication features, Alset also tracks when a vehicle is in need of
maintenance and automatically informs the owner via email.\\\\

• prices: As noted above, Alset lists prices in US dollars only and does not accept payment in any fiat
currency other than the US dollar. There are lots of prices.
There is price list for currently-offered vehicles and options (such as auto-pilot, ejector seats, and the
like). Some options are available individually while others are grouped into packages. Typically the
price of a package is less than the sum of the prices of the component options. Note that this list
is for vehicles that one can order. There need not be an actual physical vehicle with every possible
combination of options.\\
An existing vehicle has a sales price that is equal to the price determined from the price-list at the
time of its sale minus any discounts. If a vehicle is re-sold as a used vehicle, it has more than one sales
price, one for each sales transaction. Used vehicle pricing is based on the individual vehicle, so that
two identically equipped vehicles from the same model year might be sold used at different prices.
So clearly, a product does not have one single specific price. There will need to be some way to represent
all this in a reasonable way in the database. Figuring out how to represent prices in a way that leads
to a clean database design will take considerable thought.\\\\

• repairs: For a particular repair, a vehicle is serviced by a service location, certain services are provided,
and there is a price for those services. For simplicity in this project, we shall not represent the specific
list of services provided and instead record only information about the vehicle, the location, the date,
and the total cost.\\\\

## Interfaces

Customers:\\
    Should have the least access to general data. Customers should only need to access and edit personal information, such as card data, user profile data and vehicle data. They should be able to view information such as prices, vehicle info, and service locations.

Store managers:\\
    Store managers should be able to view all corperate information, but only edit information pertaining to their stores.

Product managers:\\
    Product managers should be able to view all corperate information, but only edit information pertaining to vehicles and models.