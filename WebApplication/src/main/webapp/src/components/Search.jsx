const Search = (props) => {
    console.log(props.searchTerm)
    return (
        <div className="search-bar">
            <input
                type="text"
                placeholder="Tìm kiếm sách..."
                value={props.searchTerm}
                onChange={(event) => props.handleSearch(event.target.value)}
            />
        </div>
    )
}
export default Search;