namespace java com.khaitq2.songservice
struct SongStruct{
    1: i32 id,
    2: string title,
    3: list<string> singers,
    4: i32 stream = 0,
    5: i32 like = 0,
}
struct SongResult{
    1: required Error error,
    2: optional SongStruct song,
}
struct ListSongResult{
    1: required Error error,
    2: optional list<i32> listSong,

}
service SongService {
    Error put(1: string title, 2: list<string> singers),
    SongResult get(1: i32 id),
    Error remove(1: i32 id),

    Error stream(1: i32 id),
    Error like(1: i32 id),
    Error unlike(1: i32 id),

    ListSongResult getTopStream(1: i32 topx),
    ListSongResult getTopLike(1: i32 topx),
    ListSongResult getListSongOfSinger(1: string name),
}

enum Error{
    FAIL,
    SUCCESS,
    INVALID_DATA
}