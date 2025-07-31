<#import "blocks/template.ftlh" as t>
<@t.template user>
    <h1 style="text-align: center">Searching Products</h1><hr>
    <form method="get" action="/">
        <div class="form-row">
            <div class="form-group col-md-2">
                <label for="searchCity">State</label>
                <select id="searchCity" name="searchCity" class="form-control">
                    <option value="">State don't chose</option>
                    <option value="LosAngeles" <#if searchCity?? && searchCity == "LosAngeles">selected</#if>>LA</option>
                    <option value="NewYork" <#if searchCity?? && searchCity == "NewYork">selected</#if>>NY</option>
                    <option value="Florida" <#if searchCity?? && searchCity == "Florida">selected</#if>>FL</option>
                </select>
            </div>
            <div class="form-group col-md-10">
                <label for="searchWord">Search by advertisements</label>
                <input type="text" name="searchWord" <#if searchWord??>value="${searchWord}"</#if> class="form-control" id="searchWord">
            </div>
            <button style="width: 100%" type="submit" class="btn btn-dark">Search</button>
        </div>
    </form>
    <hr>
    <div class="card-columns">
        <#list products as product>
            <a href="/product/${product.id}" class="product-link">
                <div class="card text-white bg-dark my-3 product">

                    <img src="/images/${product.previewImageId}"
                         class="card-img-top">
                    <div class="card-body">
                        <h5 class="card-title">${product.title}</h5>
                        <p class="card-text">${product.price} $</p>
                    </div>
                    <div class="card-footer">
                        ${product.city}
                    </div>
                </div>
            </a>
        <#else>
            <h1 style="text-align: center">Not found</h1>
        </#list>
    </div>
</@t.template>